package com.example.umc9th.domain.shop.entity;

import com.example.umc9th.domain.shop.enums.ShopType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name", length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShopType shopType;

    @Column(name = "shop_address", nullable = false)
    private String address;

    @Column(nullable = false)
    private int number;
}
