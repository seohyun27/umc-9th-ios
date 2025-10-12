package com.example.umc9th.domain.cs.entity;

import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cs")
public class CS {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cs_id")
  private Long id;

  @Column(name = "cs_title", length = 20, nullable = false)
  private String title;

  @Column(name = "cs_content")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cs_type_id")
  private CStype type;
}
