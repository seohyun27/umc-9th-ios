package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "review_id")
  private Long id;

  @Column(name = "review_rate")
  @Builder.Default
  private int rate = 0;

  @Column(name = "review_content")
  private String content;

  @OneToMany(fetch = FetchType.LAZY)
  private Store store;
}
