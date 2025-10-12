package com.example.umc9thspringdemo.domain.store.entity;

import com.example.umc9thspringdemo.domain.member.entity.Food;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {

    @Id
    @Column(name = "rest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "addr", nullable = false)
    private String address;

    @Column(name = "rest_value")
    private String restValue;

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
