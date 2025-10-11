package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.mission.enums.Status;
import com.example.umc9th.domain.shop.entity.Shop;
import com.example.umc9th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
@Table(name ="mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @Column(name = "success_time")
    private LocalDateTime successTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.IN_PROCESS;

    @Column(name = "auth_code")
    private String authCode;

    @Column(name = "mission_point", nullable = false)
    private Integer missionPoint;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name="shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;
}