package ru.retail.expert.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "material_id", nullable = false)
    private Long materialId;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long categoryCode;
    @Column(nullable = false)
    private String categoryName;
    @OneToMany
    @JoinColumn(name = "material_id")
    private List<Price> prices;
}
