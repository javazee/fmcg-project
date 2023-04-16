package ru.retail.expert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PriceId implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Product product;
    @Column(nullable = false)
    private String chainName;
}
