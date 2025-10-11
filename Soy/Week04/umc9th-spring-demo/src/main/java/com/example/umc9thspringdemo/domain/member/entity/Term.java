package com.example.umc9thspringdemo.domain.member.entity;

import com.example.umc9thspringdemo.domain.member.enums.TermName;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @Column(name = "term_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermName termName;

    @Column(name = "desc")
    private String desc;

    @Column(name = "status", nullable = false)
    private boolean isValid;
}
