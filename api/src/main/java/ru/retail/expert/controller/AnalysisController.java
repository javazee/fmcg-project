package ru.retail.expert.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.retail.expert.model.LoadShipmentsDto;

public interface AnalysisController {

    @GetMapping
    ResponseEntity<?> analyseAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                 @RequestParam(value = "perPage", defaultValue = "50") Integer perPage);

    @PostMapping
    ResponseEntity<?> loadByFilters(@RequestBody LoadShipmentsDto loadShipmentsDto,
                                    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                    @RequestParam(value = "perPage", defaultValue = "100") Integer perPage);
}
