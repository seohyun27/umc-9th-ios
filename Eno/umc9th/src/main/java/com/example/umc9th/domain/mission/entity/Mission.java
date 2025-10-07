package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.shop.entity.Shop;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private char dtype;

    @Column(name = "mission_text", nullable = false)
    private String text;

    @Column(name = "mission_point", nullable = false)
    private int point;

    @Column(nullable = false)
    private LocalDateTime finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
