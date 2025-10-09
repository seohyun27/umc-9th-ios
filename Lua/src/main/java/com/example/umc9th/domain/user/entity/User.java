package com.example.umc9th.domain.user.entity;

import com.example.umc9th.domain.user.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor(access= AccessLevel.PRIVATE)
@Getter
@Table(name ="user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name="birth")
    private LocalDate birth;

    @Column(name="address", length = 50, nullable = false)
    private String address;

    @Column(name="login_id", length = 10, nullable = false)
    private String loginId;

    @Column(name="password", length = 15, nullable = false)
    private String password;

    @CreatedDate
    @Column(name="created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name="inactive_time")
    private LocalDateTime inactive_time;

    @Column(name="phone_number", length=15)
    private String phoneNumber;
}
