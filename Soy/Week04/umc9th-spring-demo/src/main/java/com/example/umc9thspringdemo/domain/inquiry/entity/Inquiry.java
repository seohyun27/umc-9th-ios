package com.example.umc9thspringdemo.domain.inquiry.entity;

import com.example.umc9thspringdemo.global.entity.BaseEntity;
import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.domain.inquiry.enums.InquiryType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "inquiry")
public class Inquiry extends BaseEntity {

    @Id
    @Column(name = "inquiry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "dtype")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private InquiryType inquiryType = InquiryType.OTHER;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
