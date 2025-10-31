package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Perform;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PerformRepository extends JpaRepository<Perform, Long> {

    // 3번 진행중/완료 미션 조회
    @Query("SELECT p FROM Perform p " +
            "JOIN FETCH p.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE p.member.id = :memberId " +
            "AND p.status = :status")
    List<Perform> findByMemberIdAndStatusWithMissionAndStore(
            @Param("memberId") Long memberId,
            @Param("status") boolean status
    );

    // 4번 홈 화면 - 도전 가능한 미션 조회 (첫 페이지)
    @Query("SELECT p FROM Perform p " +
            "JOIN FETCH p.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE p.member.id = :memberId " +
            "AND p.status = false " +
            "AND s.region.unit = :regionName " +
            "ORDER BY m.endDate DESC, p.id DESC")
    List<Perform> findAvailableMissionsByRegionFirstPage(
            @Param("memberId") Long memberId,
            @Param("regionName") String regionName,
            Pageable pageable
    );

    // 4번 홈 화면 - 도전 가능한 미션 조회 (커서 페이징)
    @Query("SELECT p FROM Perform p " +
            "JOIN FETCH p.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE p.member.id = :memberId " +
            "AND p.status = false " +
            "AND s.region.unit = :regionName " +
            "AND (m.endDate, p.id) < (:lastEndDate, :lastId) " +
            "ORDER BY m.endDate DESC, p.id DESC")
    List<Perform> findAvailableMissionsByRegionAfterCursor(
            @Param("memberId") Long memberId,
            @Param("regionName") String regionName,
            @Param("lastEndDate") LocalDateTime lastEndDate,
            @Param("lastId") Long lastId,
            Pageable pageable
    );
}