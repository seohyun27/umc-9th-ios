package com.example.umc9thspringdemo.domain.review.entity.mapping;

import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.domain.review.entity.Review;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "comment")
public class ReviewComment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
