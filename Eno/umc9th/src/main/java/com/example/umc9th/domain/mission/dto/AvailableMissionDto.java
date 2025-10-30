package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AvailableMissionDto {
    private char dtype;
    private String missionText;
    private int missionPoint;
    private LocalDateTime finishDate;
    private String shopName;
}