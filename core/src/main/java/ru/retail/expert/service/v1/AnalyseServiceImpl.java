package ru.retail.expert.service.v1;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.retail.expert.mapper.ShipmentMapper;
import ru.retail.expert.model.LoadShipmentsDto;
import ru.retail.expert.model.Response;
import ru.retail.expert.model.Shipment;
import ru.retail.expert.model.ShipmentStats;
import ru.retail.expert.dao.AnalyseDao;
import ru.retail.expert.repository.ShipmentRepository;
import ru.retail.expert.service.AnalyseService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AnalyseServiceImpl extends AnalyseService {

    private final AnalyseDao analyseDao;
    private final ShipmentMapper shipmentMapper;
    private final ShipmentRepository shipmentRepository;

    public AnalyseServiceImpl(AnalyseDao analyseDao,
                              ShipmentMapper shipmentMapper,
                              ShipmentRepository shipmentRepository) {
        this.analyseDao = analyseDao;
        this.shipmentMapper = shipmentMapper;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public Response<?> analyseAll(Integer offset, Integer perPage) {
        try {
            log.debug("Starting analysing statistics for shipments...");
            List<ShipmentStats> shipmentStats = analyseDao.getFullStats(offset, perPage)
                    .stream()
                    .collect(Collectors.groupingBy(shipment ->
                                    new ShipmentStats.GroupBy(shipment.getChainName(), shipment.getCategoryCode(), shipment.getShipmentMonth()),
                            Collectors.toList()))
                    .entrySet()
                    .stream()
                    .map(entry -> {
                        List<ShipmentStats> groupedShipments = entry.getValue();
                        ShipmentStats.GroupBy groupBy = entry.getKey();
                        Map<String, Long> volumeByPriceTag = groupedShipments
                                .stream()
                                .collect(Collectors.groupingBy(ShipmentStats::getPriceTag,
                                        Collectors.summingLong(ShipmentStats::getVolumeSum)));
                        Long promoVolume = volumeByPriceTag.get("PROMO");
                        Long regularVolume = volumeByPriceTag.get("REGULAR");
                        double promoPart = (double) promoVolume / (promoVolume + regularVolume);
                        return new ShipmentStats(groupBy, promoVolume, regularVolume, promoPart);
                    }).collect(Collectors.toList());
            log.info("Analysing statistics is completed");
            return new Response<>(shipmentStats);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new Response<>(-1, "Error occurred when analysing statistics");
        }
    }

    @Override
    public Response<?> analyseByFilter(LoadShipmentsDto loadShipmentsDto, Integer offset, Integer perPage) {
        List<Shipment> shipments = shipmentRepository.loadShipments(loadShipmentsDto.getDates(),
                loadShipmentsDto.getChains(),
                loadShipmentsDto.getProducts(),
                PageRequest.of(offset, perPage, Sort.by("shipment_date", "chain_name", "material_id").ascending()));
        log.info("Got filtered shipments by filter: {}", loadShipmentsDto.toString());
        return new Response<>(shipmentMapper.toShipmentDtoList(shipments));
    }
}
