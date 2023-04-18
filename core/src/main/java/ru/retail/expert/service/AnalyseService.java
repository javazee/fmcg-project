package ru.retail.expert.service;

import ru.retail.expert.model.LoadShipmentsDto;
import ru.retail.expert.model.Response;

public abstract class AnalyseService {

    public abstract Response<?> analyseAll(Integer offset, Integer perPage);

    public abstract Response<?> analyseByFilter(LoadShipmentsDto loadShipmentsDto, Integer offset, Integer perPage);
}
