package com.example.umc9thspringdemo.domain.mission.entity;

import com.example.umc9thspringdemo.domain.store.entity.Restaurant;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @Column(name = "mission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Long point = 1000L;

    @Column(name = "start_date")
    private LocalDateTime startAt;

    @Column(name = "deadline")
    private LocalDateTime endAt;

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;
}
