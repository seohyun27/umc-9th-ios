## 1주차 미션 리팩토링

```java
public interface ReviewRepository extends JpaRepository<Review, Long> {

	Review findById(Long id);
}

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findById(Long id);
}

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

	@Query("select sum(h.point) from PointHistory h where h.member_id = :memberId")
	Long sumTotalPointsByMemberId(@(Param("memberId") Long memberId);
}
```

```java
### 리뷰 작성
{
	...
	Review review = reviewRepository.findById(memberId); #id: 프론트에서 받아옴
	Member member = memberRepository.findById(review.memberId);
	...
}
```

### 마이페이지
```java
{
	...
	Member member = memberRepository.findById(memberId);
	Long points = pointHistoryRepository.sumTotalPointsByMemberId(memberId);
	...
}
```

### 미션 목록
```java
{
	...
	List<Mission> completeMissions = missionRepository.findByIdAndCompletedIsTrue(memberId);

	List<Mission> uncompleteMissions = missionRepository.findByIdAndCompletedIsFalse(memberId);
	# 엥 이거 N + 1
	Restaurant restaurant = RestaurantRepository.findByMissionId(missionId);
	...
}
```

### 홈 화면
```java
{
	...
	# ??
	Mission mission = MissionRepository.findBy();
	# N + 1
	Restaurant restaurant = RestaurantRepository.findByMissionId(missionId);
	Food food = FoodRepository.findById(foodId);
	...
}
```

# 페이징
# 메소드
