package ru.retail.expert.service;

import ru.retail.expert.model.PriceDto;
import ru.retail.expert.model.Response;

import java.util.List;

public abstract class PriceService {
    public abstract Response<?> addPrice(PriceDto priceDto);

    public abstract Response<?> updatePrice(PriceDto priceDto);

    public abstract Response<?> deletePrice(Long materialId, String chainName);

    public abstract Response<?> getPrice(Long materialId, String chainName, int offset, int perPag);
}
