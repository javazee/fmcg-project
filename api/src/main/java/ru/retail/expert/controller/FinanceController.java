package ru.retail.expert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.retail.expert.model.PriceDto;

public interface FinanceController {

    @PostMapping("/new")
    ResponseEntity<?> createPrice(@RequestBody PriceDto priceDto);

    @PutMapping("/update")
    ResponseEntity<?> updatePrice(@RequestBody PriceDto priceDto);

    @GetMapping
    ResponseEntity<?> getPrice(@RequestParam(value = "material_id", required = false) Long materialId,
                               @RequestParam(value = "chain_name", required = false) String chainName,
                               @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                               @RequestParam(value = "perPage", defaultValue = "10") Integer perPage);

    @DeleteMapping
    ResponseEntity<?> deletePrice(@RequestParam(value = "material_id", required = false) Long materialId,
                                  @RequestParam(value = "chain_name", required = false) String chainName);
}
