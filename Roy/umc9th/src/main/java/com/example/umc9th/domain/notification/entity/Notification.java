package com.example.umc9th.domain.notification.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification")
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notification_id")
  private Long id;

  @Column(name = "notification_type")
  private String type;
}
