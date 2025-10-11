package com.example.umc9thspringdemo.domain.member.entity.mapping;

import com.example.umc9thspringdemo.domain.member.entity.Food;
import com.example.umc9thspringdemo.domain.member.entity.Member;
import jakarta.persistence.*;

public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
