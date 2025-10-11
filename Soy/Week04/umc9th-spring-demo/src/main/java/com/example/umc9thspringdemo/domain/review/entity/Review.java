package com.example.umc9thspringdemo.domain.review.entity;

import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.domain.store.entity.Restaurant;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;

public class Review extends BaseEntity {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;
}
