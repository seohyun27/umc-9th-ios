package com.example.umc9th.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "mission")
public class Mission {

  @Id
  @Column(name = "mission_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "mission_point", nullable = false)
  @Builder.Default
  private Long point = 0L;

  @Column(name = "standard_amount", nullable = false)
  private Long standard_amount = 0L;
}
