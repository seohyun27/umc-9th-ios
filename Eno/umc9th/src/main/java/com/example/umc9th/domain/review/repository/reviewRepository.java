package com.example.umc9th.domain.review.repository;
import com.example.umc9th.domain.review.entity.ShopReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JPQL
// DB 테이블이 아닌 자바 엔티티를 기준으로 쿼리를 작성
// JOIN : 리뷰는 항상 작성자를 가져야 함 (Inner Join)
// FETCH : 해당 필드(FK)에 연결된 테이블을 가져와서 지금 조회하는 객체의 필드에 미리 채워넣는다

// JPQL에 사진 파트를 같이 넣으려 했더니 반드시 양방향 매핑을 해야하는 문제가 발생했다
// JPQL의 경우 엔티티를 기준으로 쿼리를 작성하므로 join을 위해서는 반드시 join하려는 테이블의 FK를 알고 있어야 한다

public interface reviewRepository extends JpaRepository<ShopReview, Long> {

    // 특정 가게의 리뷰 리스트 가져오기 (사진 제외)
    @Query("""
       SELECT sr
       FROM ShopReview sr
       JOIN FETCH sr.member m
       WHERE sr.shop.id = :shopId
       """)
    List<ShopReview> findAllByShopIdWithMember(@Param("shopId") Long shopId);
}
