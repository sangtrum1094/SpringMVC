package com.sang.minishops.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Product(int id, String productName){
       this.productName =productName;
    }
}
