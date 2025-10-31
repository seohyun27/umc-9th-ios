package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.MemberType;
import com.example.umc9th.domain.mission.entity.Perform;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

  @Id
  @Column(name = "member_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 10, nullable = false)
  private String name;

  @Column(name = "gender", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private Gender gender = Gender.NONE;

  @Column(name = "birth_date", nullable = true)
  private LocalDate birth_date;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "member_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private MemberType type;

  @Column(name = "point", nullable = true)
  @Builder.Default
  private Long point = 0L;

  @Column(name = "inactivate_at", nullable = true)
  private LocalDateTime inactivate_at;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Perform> performs = new ArrayList<>();
}
