package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc9th.domain.mission.dto.MyMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 현재 선택된 유저의 모든 멤버 미션 가져오기

     * MemberMission에다 멤버미션.mission과 미션.shop을 join
     * 이후 특정 멤버의 id로 MemberMission 테이블을 필터링
     * 미션의 종료날짜를 기준으로 정렬한다
     * 미션의 달성 여부, 미션의 포인트, 가게의 이름 등의 정보를 dto에 채워넣는다
     **/
    @Query("""
       SELECT new com.example.umc9th.domain.mission.dto.MyMissionDto(
           um.isCompleted,
           um.finishAt,
           m.dtype,
           m.text,
           m.point,
           m.finishDate,
           s.name
       )
       FROM MemberMission um
       JOIN um.mission m
       JOIN m.shop s
       WHERE um.member.id = :memberId
       ORDER BY m.finishDate DESC
       """)
    List<MyMissionDto> findMyMissionsByMemberId(@Param("memberId") Long memberId);



    /**
     * 현재 선택 된 지역에서 도전이 가능한 미션 목록 가져오기

     * Mission에다가 미션.shop 필드의 FK(Shop 엔티티 테이블)를 join
     * 이후 미션.address가 :addressPattern이라는 변수 안의 문자열을 가지고
     * 미션.finishDate가 CURRENT_TIMESTAMP보다 크거나 같은 것만을 걸러낸다
     * 필터링된 데이터를 미션.finishDate → 미션.id 순으로 정렬한다
     * 정렬이 끝난 데이터를 AvailableMissionDto에 채워넣는다

     * CURRENT_TIMESTAMP : JPQL 표준 기능으로 쿼리가 실행되는 시점의 DB 서버 현재 시간을 자동으로 가져온다
     **/
    @Query("""
           SELECT new com.example.umc9th.domain.mission.dto.AvailableMissionDto(
               m.dtype,
               m.text,
               m.point,
               m.finishDate,
               s.name
           )
           FROM Mission m
           JOIN m.shop s
           WHERE s.address LIKE :addressPattern
             AND m.finishDate >= CURRENT_TIMESTAMP
           ORDER BY m.finishDate DESC, m.id DESC
           """)
    List<AvailableMissionDto> findAvailableMissionsByAddress(
            @Param("addressPattern") String addressPattern
    );
}