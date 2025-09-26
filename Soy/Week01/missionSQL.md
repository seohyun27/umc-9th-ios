#미션 내용

##Week00 DB 기반으로 다음에 대한 쿼리를 작성.

1. 리뷰 작성
2. 마이 페이지
3. 사용자의 진행중/진행 완료 미션 목록(페이징 포함)
4. 홈 화면

##DB 확인사항
워크북 DB와 내 DB를 비교했을 때, 다음과 같은 차이점이 있었다.
1. 소셜 로그인 방식
2. 사장님 확인번호가 가게 구분에 사용됨(내 DB에서는 미션 인증번호)
3. 지역 확장성을 고려한 주소 테이블 분리
4. 미션 제목/내용이 아닌 미션 조건으로 통합 관리함

##미션 진행

###리뷰 작성
```sql
SELECT
	A.NICKNAME
	, B.RATING
	, B.CONTENT
	, B.CREATED_AT
FROM
	MEMBER A
	JOIN REVIEW B
	ON A.ID = B.MEMBER_ID
WHERE
	B.ID = ?; #프론트에서 받아오는 값, 방금 작성한 리뷰 ID
```
REVIEW 테이블에서 해당 리뷰를 먼저 찾고 사용자들 찾아야 성능이 좋지 않을까?
>> 쿼리 옵티마이저가 자동 수행함!

###마이페이지
```sql
SELECT
	A.NICKNAME
	, A.EMAIL
	, A.PHONE
	, SUM(B.POINT)
FROM
	MEMBER A
	JOIN POINT_HISTORY B ON A.ID = B.MEMBER_ID
WHERE
	A.ID = ? #회원ID를 받아옴
	AND B.STATUS = TRUE;
```

###미션 목록
```sql
SELECT
	A.TITLE
	, A.CONTENT
	, A.POINT
	, B.NAME
	, C.COMPLETE
FROM
	MISSION A
	LEFT JOIN RESTAURANT B ON A.REST_ID = B.ID
	LEFT JOIN MEMBER_MISSION C ON A.ID = C.MISSION_ID
WHERE
	A.ID = ? #회원ID를 받아옴
	AND C.ID < ? #마지막으로 출력한 수행미션ID
ORDER BY
	C.ID DESC
LIMIT
	6;
```

###홈 화면
```sql
SELECT
	A.TITLE
	, A.CONTENT
	, A.POINT
	, A.DEADLINE
	, B.NAME AS restaurent_name
	, C.NAME AS food_category
FROM
	MISSION A
	JOIN RESTAURANT B ON A.REST_ID = B.ID
	JOIN FOOD C ON B.FOOD_ID = C.ID
WHERE
	B.ADDR = ? #주소 테이블 분리 필요, 이 경우 추가 조인 필요
	AND NOT EXIST (
		SELECT
			1
		FROM
			MEMBER_MISSION X
		WHERE
			X.MISSION_ID = A.ID
			AND X.ID = ? #사용자ID
	)
	AND A.ID < ? #마지막으로 출력한 수행미션ID
ORDER BY
	A.ID DESC
LIMIT
	6;
```

####조금 더?
안암동의 미션을 몇 개 달성했는지 반환하는 쿼리
```sql
SELECT
	COUNT(*)
FROM
	RESTAURANT X
	JOIN MISSION Y ON X.ID = Y.REST_ID
	JOIN MEMBER_MISSION Z ON Y.ID = Z.MISSION_ID
WHERE
	X.ADDR = ? #선택 주소
	AND Z.MEMBER_ID = ? #로그인한 사용자ID
	AND Z.COMPLETE = TRUE;
```

####조금만 더?
미확인 알람이 있는지 알려주는 쿼리
```sql
SELECT
	1
FROM
	MEMBER A
	JOIN ALARM B ON B.MEMBER_ID = A.ID
WHERE
	EXIST (
		B.CHECK = FALSE
		AND A.ID = ?
	);
