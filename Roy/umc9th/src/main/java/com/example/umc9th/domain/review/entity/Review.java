package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Long id;

  @Column(name = "review_rate")
  @Builder.Default
  private int rate = 0;

  @Column(name = "review_content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;  // 리뷰 작성자

    @Column(name = "reply_content")
    private String reply;  // 답글 내용

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();
}
