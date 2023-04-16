package ru.retail.expert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PriceDto {
    @JsonProperty("material_id")
    private Long materialId;
    @JsonProperty("chain_name")
    private String chainName;
    @JsonProperty("regular_price")
    private Double regularPrice;

    @Override
    public String toString() {
        return "PriceDto{" +
                "materialId=" + materialId +
                ", chainName='" + chainName + '\'' +
                ", regularPrice=" + regularPrice +
                '}';
    }
}
