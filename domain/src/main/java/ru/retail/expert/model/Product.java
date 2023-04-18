package ru.retail.expert.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @JoinColumn(name = "material_id")
    @LazyCollection(value = LazyCollectionOption.TRUE)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Price> prices;
}
