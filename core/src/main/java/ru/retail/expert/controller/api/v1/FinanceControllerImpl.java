package ru.retail.expert.controller.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.retail.expert.controller.FinanceController;
import ru.retail.expert.model.PriceDto;
import ru.retail.expert.service.PriceService;
import ru.retail.expert.service.v1.PriceServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/finance")
public class FinanceControllerImpl implements FinanceController {

    private final PriceService priceService;

    @Override
    public ResponseEntity<?> createPrice(PriceDto priceDto) {
        return ResponseEntity.ok(priceService.addPrice(priceDto));
    }

    @Override
    public ResponseEntity<?> updatePrice(PriceDto priceDto) {
        return ResponseEntity.ok(priceService.updatePrice(priceDto));
    }

    @Override
    public ResponseEntity<?> getPrice(Long materialId, String chainName, Integer offset, Integer perPag) {
        return ResponseEntity.ok(priceService.getPrice(materialId, chainName, offset, perPag));
    }

    @Override
    public ResponseEntity<?> deletePrice(Long materialId, String chainName) {
        return ResponseEntity.ok(priceService.deletePrice(materialId, chainName));
    }
}
