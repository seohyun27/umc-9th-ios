package com.example.umc9thspringdemo.domain.member.entity.mapping;

import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.domain.member.entity.Term;
import com.example.umc9thspringdemo.global.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "agree_term")
public class MemberTerm extends BaseEntity {

    @Id
    @Column(name = "agree_term_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private boolean isAgreed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;
}
