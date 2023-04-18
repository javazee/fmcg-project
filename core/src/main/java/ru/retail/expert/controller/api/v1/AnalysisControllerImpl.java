package ru.retail.expert.controller.api.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.retail.expert.controller.AnalysisController;
import ru.retail.expert.model.LoadShipmentsDto;
import ru.retail.expert.service.v1.AnalyseServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/analyse")
public class AnalysisControllerImpl implements AnalysisController {

    private final AnalyseServiceImpl analyseService;

    @Override
    public ResponseEntity<?> analyseAll(Integer offset, Integer perPage) {
        return ResponseEntity.ok(analyseService.analyseAll(offset, perPage));
    }

    @Override
    public ResponseEntity<?> loadByFilters(LoadShipmentsDto loadShipmentsDto, Integer offset, Integer perPage) {
        return ResponseEntity.ok(analyseService.analyseByFilter(loadShipmentsDto, offset, perPage));
    }


}
