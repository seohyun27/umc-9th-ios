package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED) // Builder.Default를 위해 필요
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(name = "member_name", length = 5, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    private LocalDate birthDate;

    @Column(name = "member_address")
    private String address;

    private LocalDateTime inactiveDate;
}
