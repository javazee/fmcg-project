package ru.retail.expert.model;

import lombok.Data;

@Data
public class ShipmentDto {
    private Long id;
    private Long materialId;
    private Long customerId;
    private String chainName;
    private Integer volume;
    private Double totalCost;
    private String priceTag;
}
