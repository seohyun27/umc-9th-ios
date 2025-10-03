# Mission : 0,1주차때 설계했던 DB, sql 문 참고해서 API 명세 만들어 보기

## ➡️ 모든 API

| 기능                           | method | url                         | query-string                      | body                                    |
| ------------------------------ | ------ | --------------------------- | --------------------------------- | --------------------------------------- |
| 미션 조회 (미션 목록/ 홈 화면) | GET    | users/missions         | region, end_date, end_id, enabled | -                                       |
| 리뷰 작성                      | POST   | stores/{store-id}/review    | -                                 | content, rate, images                   |
| 미션 성공 누르기               | PUT    | users/missions/{mission-id} | -                                 | status, date                            |
| 회원가입                       | POST   | auth/users/sign-up               | -                                 | name, gender, birth-date, address, type |

## 1. 홈 화면

현재 선택된 지역에서 도전 가능한 미션 목록을 가져옵니다.

```sql
select * from perfom p
join mission m on p.mission_id = m.id
join store s on m.store_id = s.id
where p.user_id = 유저 아이디 and p.status = 0
and s.region_id = (select r.id from regoin r where unit = 지역명)
and (p.end_date > 마지막 날짜 or (p.end_date = 날짜 and p.id > 마지막 id))
order by p.end_date desc, p.id desc
limit 10;
```

1. 먼저 현재 선택된 지역을 알아야 합니다.
2. 모든 미션 중에 가능한 미션을 가져오는 것입니다.
3. SQL 쿼리에서 페이징을 구현했기 때문에 쿼리 스트링으로 마지막 날짜, 마지막 id를 전달해야 합니다.
4. user-id는 path로 넘기지 않고 인증을 통해 구현됩니다.

**API 설계**

- method : GET
- endpoint: `users/missions`
- header: jwt 인증토큰
- query-string: `?region=현재지역&end_date=날짜&end_id=아이디&enabled=true`
- path-variable: -
- request-body: -

---

## 2. 가게 페이지 리뷰 작성

1. 어떤 가게에 작성하는 지 알아야 합니다.
2. 리뷰를 남기려면 별점, 내용, 사진, 등록날짜가 필요합니다.
3. 하나의 가게에 리뷰를 남기는 것이므로 가게를 상위 개념으로 보았습니다.

**API 설계**

- method: POST
- endpoint: `stores/{store-id}/review`
- header: jwt 인증토큰
- query-string: -
- path-variable: store-id
- request-body

```json
{
  "content": "리뷰 내용",
  "rate": "별점",
  "images": ["image_url_1", "image_url_2"]
}
```

---

## 3. 미션 목록 조회

- 미션 목록 조회도 홈 화면과 같은 api를 사용하고 쿼리 스트링으로 옵션을 지정합니다.
- enabled로 미션 중, 미션 완료 두 가지 경우를 다 가져옵니다.
- 지역 옵션을 따로 지정하지 않았기 때문에 자동으로 all 처리가 됩니다.

```sql
select * from perform p
JOIN mission m on p.mission_id = m.id
JOIN store s on m.store_id = s.id
where p.user_id = 유저 아이디 and p.status = 수행 여부;
```

**API 설계**

- method : GET
- endpoint: `users/missions`
- header: jwt 인증토큰
- query-string: `end_date=날짜&end_id=아이디&enabled={true/false}`
- path-variable: -
- request-body: -

---

## 4. 미션 성공 누르기

미션의 상태를 true로 바꿔줍니다.

**API 설계**

- method: PUT
- endpoint: `users/missions/{mission-id}`
- header: jwt 인증토큰
- query-string: -
- path-variable: mission-id
- request-body

```json
{
  "status": true,
  "date": "현재 날짜"
}
```

---

## 5. 회원가입하기

이름, 성별, 생년월일, 주소, 타입을 넘겨줍니다.

**API 설계**

- method: POST
- endpoint: `auth/users/sign-up`
- header: -
- query-string: -
- path-variable: -
- request-body

```json
{
  "name": "이름",
  "gender": "성별",
  "birth-date": "생년월일",
  "address": "주소",
  "type": "일반 사용자 / 매니저"
}
```
