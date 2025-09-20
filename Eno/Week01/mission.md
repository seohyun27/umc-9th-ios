## ✅ 미션
### 특정 가게에 달린 모든 리뷰 가져오기
```sql
SELECT *
FROM shop_review AS sr
INNER JOIN shop AS s ON sr.shop_id = s.id
INNER JOIN user AS u ON sr.user_id = u.id
LEFT JOIN review_photo AS rp ON sr.id = rp.review_id
WHERE sr.shop_id = ?;
```
- 가게 리뷰를 조회할 때 가게와 유저는 반드시 존재하여야 한다 → INNER JOIN 사용
- 이때의 조건은 가게 리뷰의 가게 아이디와 유저 아이디가 해당 테이블들의 PK와 일치하여야 한다
- 리뷰 사진은 있을 수도 있고 없을 수도 있으므로 LEFT JOIN을 사용한다. review_photo의 리뷰 id와 리뷰 테이블의 PK가 일치하여 한다
- 완성된 가상 테이블에서 WHERE문을 사용해 찾으려는 가게 id과 리뷰 테이블의 shop_id가 일치하는 데이터만을 select 한다
- ?의 자리에 프론트에서 받아온 가게 id를 삽입할 수 있다
- 필요하다면 SELECT의 뒤에 원하는 데이터만을 명시할 수 있다

<br/>

### 마이 페이지 정보 가져오기
```sql
SELECT u.user_name, u.gender, u.birth_date, u.user_address,
       SUM(m.point) AS total_point
FROM user AS u
LEFT JOIN user_mission AS um ON u.user_id = um.user_id
LEFT JOIN mission AS m ON um.mission_id = m.mission_id
WHERE u.user_id = ?
GROUP BY u.user_id, u.user_name, u.gender, u.birth_date, u.address;
```
- user 테이블 가져오기
- user 테이블에 user_mission 테이블을 LEFT JOIN
- 이때 유저 id가 일치하는 데이터들만 join 할 것
- 위의 결과 테이블에 mission 테이블을 LEFT JOIN
- 이때 미션 id가 일치하는 데이터들만 join 할 것
- 결과 테이블에서 프론트가 요청한 user_id를 가진 데이터만을 가져올 것
#### GROUP BY
- 이후 GROUP BY를 이용해 u.user_id를 기준으로 그룹을 묶어준다
- 만약 유저가 3번의 미션을 수행했다면 point외에 유저의 정보는 3번으로 중복되어 표현된다
- 그러나 GROUP BY를 사용하면 필터링된 3줄의 데이터를 단 하나의 그룹으로 묶어버린다
- 즉, 3줄의 데이터를 단 하나의 행으로 요약해 주는 것이다
#### GROUP BY 뒤에 user의 나머지 칼럼을 명시한 이유
- 엄격한 DB의 경우 SELECT 절에 작성되어 있지만 집계 함수로 묶이지 않은 모든 컬럼은 반드시 GROUP BY 절에 명시되어야 한다는 규칙이 존재한다
- 따라서 모든 칼럼을 GROUP BY에 적어주는 편이 호완성 높은 코드를 작성하기에 유리하다
#### 결론
- 이후 완성된 테이블에서 user의 기본 정보 칼럼들(name, gender, birth_date 등등)을 가져오며 SUM(m.point)를 사용해 합계 포인트를 가져온다
- 이때 AS를 사용해 만들어진 결과에 total_point라는 별칭을 붙여준다 → 사용이 편리해진다
- SUM(m.point) 대신 COALESCE(SUM(m.point), 0)을 사용하면 SUM(m.point)이 NULL값일 때 0을 반환하도록 쿼리를 수정할 수 있다
#### Q. 쿼리문을 한 번에 작성하는 이유
- 유저의 정보 가져오기, 해당 유저의 포인트 계산하기 이렇게 두 개의 정보를 원하는 경우에도 한 페이지의 내용은 한 번의 쿼리로 작성되는 것이 좋다
- 두 번에 나눠 쿼리를 작성할 경우 DB에 두 번의 요청이 가게 되므로 비효율적이다
- 또한 두 쿼리의 실행 사이 데이터가 변경될 가능성이 있다

<br/>

### 미션 모아보기
#### 페이징 전의 코드
```sql
SELECT um.is_complete, um.finish_at, m.dtype, 
       m.mission_text, m.mission_point, m.finish_date, s.shop_name
FROM user_mission AS um
INNER JOIN mission AS m ON um.mission_id = m.mission_id
INNER JOIN shop AS s ON m.shop_id = s.shop_id
WHERE um.user_id = ?
ORDER BY m.finish_date DESC
```
- user_mission에 mission 테이블을 join 한다
- 이때 미션 id가 같은 데이터들만이 join 된다
- 결과 테이블에 shop 테이블을 join 한다 (가게 이름을 가져와야 함)
- 이후 결과 테이블에서 프론트가 요청한 user_id를 가진 데이터만을 가져온다
- 가져온 데이터들을 마감 시간을 기준으로 정렬한다

#### 페이징 후의 코드
```sql
SELECT um.is_complete, um.finish_at, m.dtype, m.mission_text,
       m.mission_point, m.finish_date, s.shop_name
FROM user_mission AS um
INNER JOIN mission AS m ON um.mission_id = m.mission_id
INNER JOIN shop AS s ON m.shop_id = s.shop_id
WHERE um.user_id = ? AND (m.finish_date, um.user_mission_id) < (?, ?)
ORDER BY m.finish_date DESC, um.user_mission_id DESC
LIMIT 10;
```
- 페이징을 위해 마감 기한과 유저 미션 id를 튜플 조건으로 사용하였다
- 페이징 중 마감 기한이 같은 값을 만날 경우 user_mission_id를 통해 순서를 구분한다
- 커서 조건 (m.finish_date, um.user_mission_id) < (?, ?)의 경우 첫 번째 페이지를 조회할 때는 사용되지 않도록 별도의 조치가 필요하다

<br/>

### 홈 화면
#### 현재 선택 된 지역에서 도전이 가능한 미션 목록 보여주기
```sql
SELECT m.dtype, m.mission_text, m.mission_point, m.finish_date, s.shop_name
FROM mission AS m
INNER JOIN shop AS s ON m.shop_id = s.shop_id
WHERE s.shop_address like ?
      AND m.finish_date >= NOW()
      AND (m.finish_date, m.mission_id) < (?, ?)
ORDER BY m.finish_date DESC, m.mission_id DESC
LIMIT 10;
```
- 미션 테이블과 shop 테이블을 shop_id를 기준으로 join
- 유저가 선택한 주소와 유사한 주소를 가지고 마감 기한이 지나지 않은 미션의 데이터들만을 반환
- 어플리케이션에서 데이터를 받아올 때 ? 자리에 들어갈 문자열 앞 뒤로 %를 붙여줄 것
- 페이징 구현
