package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.dto.AvailableMissionDto;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

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