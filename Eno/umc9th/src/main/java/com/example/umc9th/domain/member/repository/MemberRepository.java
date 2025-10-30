package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.dto.MyPageDto; // 1번에서 만든 DTO
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 마이 페이지 화면 쿼리 가져오기 (포인트 합계 포함)

     * Member에다가 MemberMission과 Mission을 join
     * 이때 Member는 MemberMission과 관련된 필드를 가지지 않으므로 MemberMission를 통째로 가져온 뒤 member_id를 통해 명시적(ON 구문 사용)으로 join한다
     * Mission의 FK는 MemberMission의 mission 필드에서 가져온다
     * 조회를 원하는 member의 id로 필터링한다
     * 조회하고자 하는 유저의 정보를 모두 그룹으로 묶고 그룹으로 묶은 정보와 합계 포인트를 MyPageDto에 채워넣는다
     **/
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