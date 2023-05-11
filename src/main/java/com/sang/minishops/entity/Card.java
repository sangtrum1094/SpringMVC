package com.sang.minishops.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "card_product",
            joinColumns = { @JoinColumn(name = "card_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private Set<Product> products = new HashSet<>();

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_date")
    private Date createdDate;

    // Các hàm getter/setter được bỏ qua để giản lược
}