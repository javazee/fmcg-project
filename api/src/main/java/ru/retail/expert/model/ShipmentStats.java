package ru.retail.expert.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShipmentStats {
    private String chainName;
    private String categoryCode;
    private Integer shipmentMonth;
    @JsonIgnore
    private Double costSum;
    @JsonIgnore
    private Long volumeSum;
    private String priceTag;
    private Long promoVolume;
    private Long regularVolume;
    private Double promoPart;


    public ShipmentStats(GroupBy groupBy, Long promoVolume, Long regularVolume, Double promoPart) {
        this.chainName = groupBy.getChainName();
        this.categoryCode = groupBy.getCategoryCode();
        this.shipmentMonth = groupBy.getShipmentMonth();
        this.promoVolume = promoVolume;
        this.regularVolume = regularVolume;
        this.promoPart = promoPart;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GroupBy {
        private String chainName;
        private String categoryCode;
        private Integer shipmentMonth;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GroupBy groupBy = (GroupBy) o;
            return Objects.equals(chainName, groupBy.chainName) && Objects.equals(categoryCode, groupBy.categoryCode) && Objects.equals(shipmentMonth, groupBy.shipmentMonth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(chainName, categoryCode, shipmentMonth);
        }
    }
}
