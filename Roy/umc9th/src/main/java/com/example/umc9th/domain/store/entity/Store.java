package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "store")
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id")
  private Long id;

  @Column(name = "store_address", length = 50, nullable = false)
  private String address;

  @Column(name = "store_name", length = 10, nullable = false)
  private String name;

  @Column(name = "open_time")
  private Timestamp open_time;

  @Column(name = "close_time")
  private Timestamp close_time;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "region_id")
  private Region region;
}
