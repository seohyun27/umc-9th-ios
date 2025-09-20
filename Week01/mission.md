### 1. 0주차 때 **직접 설계한** 데이터베이스를 토대로 아래의 화면에 대한 쿼리를 작성
#### - 설계한 DB 사진 (0주차 DB 수정 가능!)
![image.png](attachment:2f88835e-cf86-4bce-a95b-a7bcda37d23e:image.png)
### ㄴ> 홈화면 쿼리를 짤려면 shop 엔티티도 추가해야해서 전체적으로 수정할려고하는데 
### 혹시 피드백 주실 내용이 있으시면 스터디 내용과 더불어서 수정할 때 참고하겠습니다!

#### 리뷰 작성하는 쿼리
'''sql
SELECT r.*, u.user_id, u.name
FROM review AS r
JOIN user AS u ON r.user_id = u.user_id
ORDER BY r.created_at DESC;
```

#### 마이페이지 화면 쿼리 (사용자 정보, 리뷰만)
'''sql
SELECT u.name, u.email, u.phone_number, r.*
FROM user AS u
LEFT JOIN review AS r ON u.user_id=r.user_id
WHERE u.user_id=?;
```

#### 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
```sql
SELECT m.*
FROM mission AS m
JOIN user AS u ON m.user_id=u.user_id
WHERE u.user_id=?
	AND m.success=?
ORDER BY m.success_time desc
LIMIT ?;
```
##### status 값 자체로 진행여부를 판별하는 게 아니라 따로 success 값을 받아서 status 값을 처리하도록 하였다. 
##### 직관적으로 판단 가능하도록 그렇게 처리하였는데 status 값 자체를 0 or 1로 처리하면 로직이 간단하게 되기 때문에 
##### success로 따로 처리하지 말까 라는 생각이 들었다. 일단 지금 단계에서는 success로 처리하기로 했다.

#### 홈 화면 쿼리
(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
ㄴ> shop 엔티티 추가 후 작성
