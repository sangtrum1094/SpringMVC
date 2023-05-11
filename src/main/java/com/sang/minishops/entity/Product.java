package com.sang.minishops.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String productDescription;

    private Double productDiscountedPrince;

    private Double productActualPrince;

    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_images",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    private Set<Image> images = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Card> cards = new HashSet<>();
}
