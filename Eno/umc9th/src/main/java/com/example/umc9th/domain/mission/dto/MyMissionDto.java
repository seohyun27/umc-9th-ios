package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyMissionDto {

    // MemberMission 엔티티의 정보
    private boolean isCompleted;
    private LocalDateTime finishAt;

    // Mission 엔티티의 정보
    private String dtype;
    private String text;
    private Integer point;
    private LocalDate finishDate;

    // Shop 엔티티의 정보
    private String shopName;
}