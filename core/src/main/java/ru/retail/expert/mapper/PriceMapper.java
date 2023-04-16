package ru.retail.expert.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.retail.expert.model.Price;
import ru.retail.expert.model.PriceDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    static PriceMapper getInstance() {
        return Mappers.getMapper(PriceMapper.class);
    }

    @Mapping(target = "materialId", source = "price.product.materialId")
    PriceDto toDto(Price price);

    Price toEntity(PriceDto priceDto);

    List<PriceDto> toPriceList(List<Price> prices);
}
