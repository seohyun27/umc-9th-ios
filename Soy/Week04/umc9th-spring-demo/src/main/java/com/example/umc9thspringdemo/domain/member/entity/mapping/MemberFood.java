package com.example.umc9thspringdemo.domain.member.entity.mapping;

import com.example.umc9thspringdemo.domain.member.entity.Food;
import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food_like")
public class MemberFood extends BaseEntity {

    @Id
    @Column(name = "food_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
