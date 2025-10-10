package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member {
  @Id
  @Column(name = "member_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "gender")
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(name = "birth_date")
  private LocalDate birth_date;

  @Column(name = "address")
  private String address;

  @Column(name = "member_type")
  @Enumerated(EnumType.STRING)
  private MemberType type;

  @Column(name = "point")
  private Long point;

  @Column(name = "inactivate_at")
  private LocalDateTime inactivate_at;
}
