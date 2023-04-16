package ru.retail.expert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PricePk implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Product product;
    @Column(nullable = false)
    private String chainName;
}
