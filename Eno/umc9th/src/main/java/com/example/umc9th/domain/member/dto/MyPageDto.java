package com.example.umc9th.domain.member.dto;

import com.example.umc9th.domain.member.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor // JPQL의 'SELECT new...' 구문은 모든 필드를 받는 생성자가 필요합니다.
public class MyPageDto {
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private String address;
    private Long totalPoint;
}
