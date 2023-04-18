package ru.retail.expert.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.retail.expert.model.Shipment;
import ru.retail.expert.model.ShipmentDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    static ShipmentMapper getInstance() {
        return Mappers.getMapper(ShipmentMapper.class);
    }

    @Mapping(target = "materialId", source = "shipment.product.materialId")
    @Mapping(target = "customerId", source = "shipment.customer.customerId")
    ShipmentDto toDto(Shipment shipment);

    Shipment toEntity(ShipmentDto shipmentDto);

    List<ShipmentDto> toShipmentDtoList(List<Shipment> shipments);
}
