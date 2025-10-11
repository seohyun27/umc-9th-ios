package com.example.umc9thspringdemo.domain.member.entity;

import com.example.umc9thspringdemo.domain.member.enums.Gender;

import com.example.umc9thspringdemo.domain.point.entity.PointHistory;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 20, nullable = false, unique = true)
    private String loginId;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.OTHER;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "addr", nullable = false)
    private String address;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;

    @OneToMany(mappedBy = "member")
    private List<SnsLogin> snsLoginList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PointHistory> pointHistoryList = new ArrayList<>();
}
