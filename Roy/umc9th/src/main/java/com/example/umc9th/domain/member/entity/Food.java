package com.example.umc9th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "food")
public class Food {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "food_id", nullable = false)
  private Long id;

  @Column(name = "food_name", nullable = false)
  private String name;
}
