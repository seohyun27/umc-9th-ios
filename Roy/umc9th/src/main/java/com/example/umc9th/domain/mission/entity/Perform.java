package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "perform")
public class Perform {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "perform_id")
  private Long id;

  @Column(name = "mission_status", nullable = false)
  @Builder.Default
  private boolean status = false;

  @Column(name = "finished_at")
  private LocalDateTime finished_at;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mission_id")
  private Mission mission;

}
