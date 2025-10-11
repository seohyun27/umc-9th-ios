package com.example.umc9th.domain.notification.entity;

import com.example.umc9th.domain.cs.entity.CS;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reception")
public class Reception {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "reception_id")
  private Long Id;

  @CreatedDate
  @Column(name = "reception_create_at")
  private LocalDateTime creat_at;

  @Builder.Default
  @Column(name = "reception_status", nullable = false)
  private boolean status = false;

  @Column(name = "receive_at")
  private LocalDateTime receive_at;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "notification_id", nullable = false)
  private Notification notification;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id")
  private Mission mission;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "review_id")
  private Review review;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cs_id")
  private CS cs;
}
