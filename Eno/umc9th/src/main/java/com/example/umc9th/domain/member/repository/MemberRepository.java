package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.dto.MyPageDto; // 1번에서 만든 DTO
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
           SELECT new com.example.umc9th.domain.member.dto.MyPageDto(
               m.name,
               m.gender,
               m.birthDate,
               m.address,
               COALESCE(SUM(mi.point), 0L)
           )
           FROM Member m
           LEFT JOIN MemberMission mm ON m.id = mm.member.id
           LEFT JOIN mm.mission mi
           WHERE m.id = :memberId
           GROUP BY m.id, m.name, m.gender, m.birthDate, m.address
           """)
    Optional<MyPageDto> findMyPageInfoById(@Param("memberId") Long memberId);

}