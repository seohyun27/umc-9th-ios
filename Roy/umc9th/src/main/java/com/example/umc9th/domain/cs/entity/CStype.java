package com.example.umc9th.domain.cs.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cs_type")
public class CStype {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cs_type_id")
  private Long id;

  @Column(name = "cs_type_name", nullable = false, length = 5)
  private String name;
}
