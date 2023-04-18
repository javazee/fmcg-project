package ru.retail.expert.service.v1;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.retail.expert.exception.PriceNotFoundException;
import ru.retail.expert.exception.ProductNotFoundException;
import ru.retail.expert.mapper.PriceMapper;
import ru.retail.expert.model.Price;
import ru.retail.expert.model.PriceDto;
import ru.retail.expert.model.Product;
import ru.retail.expert.model.Response;
import ru.retail.expert.repository.PriceRepository;
import ru.retail.expert.repository.ProductRepository;
import ru.retail.expert.service.PriceService;

import java.util.List;

@Log4j2
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class PriceServiceImpl extends PriceService {

    private final PriceMapper priceMapper;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    public PriceServiceImpl(PriceRepository priceRepository,
                            ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.priceMapper = PriceMapper.getInstance();
        this.priceRepository = priceRepository;
    }

    @Override
    public Response<?> addPrice(PriceDto priceDto) {
        Product product = productRepository
                .findById(priceDto.getMaterialId())
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product '%d' not found", priceDto.getMaterialId())));
        Price price = priceMapper.toEntity(priceDto);
        price.setProduct(product);
        priceRepository.save(price);
        log.info("Saved new price: {}", priceDto.toString());
        return new Response<>();
    }

    @Override
    public Response<?> updatePrice(PriceDto priceDto) {
        Price price = priceRepository
                .findByMaterialIdAndChainName(priceDto.getMaterialId(), priceDto.getChainName())
                .orElseThrow(() -> new PriceNotFoundException(String.format("Price for product '%d' of chain '%s' not found", priceDto.getMaterialId(), priceDto.getChainName())));
        Double previousPrice = price.getRegularPrice();
        price.setRegularPrice(priceDto.getRegularPrice());
        priceRepository.save(price);
        log.info("Updated price for product '{}' of chain '{}': {} -> {}", priceDto.getMaterialId(), priceDto.getChainName(), previousPrice, priceDto.getRegularPrice());
        return new Response<>();
    }

    @Override
    public Response<?> deletePrice(Long materialId, String chainName) {
        if (materialId == null && chainName == null) {
            throw new PriceNotFoundException("Not enough input parameters for deleting price(s): material_id or chain_name or both are required");
        } else if (chainName == null) {
            return deletePricesByMaterialId(materialId);
        } else if (materialId == null) {
            return deletePricesByChainName(chainName);
        } else {
            return deletePriceByMaterialIdAndChainName(materialId, chainName);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Response<?> getPrice(Long materialId, String chainName, int offset, int perPage) {
        if (materialId == null && chainName == null) {
            throw new PriceNotFoundException("Not enough input parameters for getting price(s): material_id or chain_name or both are required");
        } else if (chainName == null) {
            return getPricesByMaterialId(materialId, PageRequest.of(offset, perPage, Sort.by("chain_name")));
        } else if (materialId == null) {
            return getPricesByChainName(chainName, PageRequest.of(offset, perPage, Sort.by("material_id")));
        } else {
            return getPriceByMaterialIdAndChainName(materialId, chainName);
        }
    }

    private Response<?> deletePriceByMaterialIdAndChainName(Long materialId, String chainName) {
        priceRepository.deleteByMaterialIdAndChainName(materialId, chainName);
        log.info("Deleted price for product '{}' of chain '{}'", materialId, chainName);
        return new Response<>();
    }

    private Response<?> deletePricesByChainName(String chainName) {
        priceRepository.deleteByChainName(chainName);
        log.info("Deleted prices for chain '{}'", chainName);
        return new Response<>();
    }

    private Response<?> deletePricesByMaterialId(Long materialId) {
        priceRepository.deleteByMaterialId(materialId);
        log.info("Deleted prices for product '{}'", materialId);
        return new Response<>();
    }

    private Response<PriceDto> getPriceByMaterialIdAndChainName(Long materialId, String chainName) {
        Price price = priceRepository
                .findByMaterialIdAndChainName(materialId, chainName)
                .orElseThrow(() -> new PriceNotFoundException(String.format("Price for product '%d' of chain '%s' not found", materialId, chainName)));
        log.info("Got price for product '{}' of chain '{}'", materialId, chainName);
        return new Response<>(priceMapper.toDto(price));
    }

    private Response<List<PriceDto>> getPricesByChainName(String chainName, Pageable pageable) {
        List<Price> prices = priceRepository.findPriceByChainName(chainName, pageable);
        if (prices.isEmpty()) {
            throw new PriceNotFoundException(String.format("Prices for chain '%s' not found", chainName));
        }
        log.info("Got full price list for chain '{}'", chainName);
        return new Response<>(priceMapper.toPriceList(prices));
    }

    private Response<List<PriceDto>> getPricesByMaterialId(Long materialId, Pageable pageable) {
        List<Price> prices = priceRepository.findPriceByProduct(materialId, pageable);
        if (prices.isEmpty()) {
            throw new PriceNotFoundException(String.format("Prices for product '%d' not found", materialId));
        }
        log.info("Got full price list for product '{}'", materialId);
        return new Response<>(priceMapper.toPriceList(prices));
    }
}
