package ru.retail.expert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price")
@IdClass(PricePk.class)
public class Price {
    @Id
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Product product;
    @Id
    @Column(nullable = false)
    private String chainName;
    @Column(name = "regular_price", nullable = false)
    private Double regularPrice;
}
