package com.example.umc9thspringdemo.domain.alarm.entity;

import com.example.umc9thspringdemo.domain.alarm.enums.AlarmType;
import com.example.umc9thspringdemo.domain.inquiry.entity.Inquiry;
import com.example.umc9thspringdemo.domain.member.entity.Member;
import com.example.umc9thspringdemo.domain.mission.entity.Mission;
import com.example.umc9thspringdemo.domain.review.entity.mapping.ReviewComment;
import com.example.umc9thspringdemo.domain.store.entity.Restaurant;
import com.example.umc9thspringdemo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comments;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dtype", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private AlarmType alarmType = AlarmType.OTHER;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_read", nullable = false)
    private boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = true)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = true)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id", nullable = true)
    private Inquiry inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = true)
    private ReviewComment reviewComment;

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;
}
