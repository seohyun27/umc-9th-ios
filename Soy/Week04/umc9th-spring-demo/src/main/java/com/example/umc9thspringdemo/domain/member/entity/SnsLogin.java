package com.example.umc9thspringdemo.domain.member.entity;

import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SnsLogin extends BaseEntity {

    @Id
    @Column(name = "sns_login_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sns_platform", nullable = false)
    private String platformName;

    @Column(name = "login_value", nullable = false)
    private String loginValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
