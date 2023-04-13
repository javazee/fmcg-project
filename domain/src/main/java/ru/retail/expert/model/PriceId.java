package ru.retail.expert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PriceId implements Serializable {
    @Column(name = "material_id", nullable = false)
    private Long materialId;
    @Column(nullable = false)
    private String chainName;
}
