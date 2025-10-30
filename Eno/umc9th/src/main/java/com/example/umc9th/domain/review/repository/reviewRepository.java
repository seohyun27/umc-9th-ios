package com.example.umc9th.domain.review.repository;
import com.example.umc9th.domain.review.entity.ShopReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * JPQL
 * DB 테이블이 아닌 자바 엔티티를 기준으로 쿼리를 작성
 * JOIN : 리뷰는 항상 작성자를 가져야 함 (Inner Join)
 * FETCH : 해당 필드(FK)에 연결된 테이블을 가져와서 지금 조회하는 객체의 필드에 미리 채워넣는다

 * 사진의 경우 여러 장이 존재한다면 같은 멤버 데이터에 다른 사진을 가진 여러 장의 데이터를 가져오게 되므로 사진과 관련된 구문은 생략하였다
 **/

public interface reviewRepository extends JpaRepository<ShopReview, Long> {

    /**
     * 특정 가게의 리뷰 리스트 가져오기 (사진 제외)

     * 사진의 경우 여러 장이 존재한다면 join 같은 멤버 데이터에 다른 사진을 가진 여러 장의 데이터가 생성되게 된다
     * 현재까지 이 문제를 해결할 수 없으므로 사진을 가져오는 구문은 생략한다
     **/
    @Query("""
       SELECT sr
       FROM ShopReview sr
       JOIN FETCH sr.member m
       WHERE sr.shop.id = :shopId
       """)
    List<ShopReview> findAllByShopIdWithMember(@Param("shopId") Long shopId);
}
