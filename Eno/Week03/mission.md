## ✅ 미션 기록
### 1️⃣ 홈 화면
유저 정보, 나의 미션 목록, 나의 합계 미션 점수를 받아와야 한다

```http
GET /api/users/me?include=missions

Authorization : Bearer {accessToken}
```
- user_mission 테이블의 존재는 api 상에서 표시하지 않는다
- RESTful 디자인 관점에서 URL의 마지막에 오는 명사가 이 api가 반환하는 핵심 리소스가 된다
- 만약 경로 변수를 `GET /users/{userId}/missions`로 설계한다면 특정 유저의 미션 목록을 가져오는 api로 인식된다
- 유저 미션 목록의 경우 상위 리소스인 유저를 조회하는 것에 포함된다
- 필요하다면 쿼리 스트링을 사용해 미션 정보의 포함 여부를 넣을 수 있다
- GET의 경우 요청 바디는 사용하지 않는다
- 요청 헤더에 인증이 끝난 사용자임을 알린다

<br/>

### 2️⃣ 마이 페이지 리뷰 작성
마이 페이지에서 가게 리뷰를 작성할 수 있는 건 ui적으로 어색함으로 '내가 작성한 리뷰들 모아보는' 기능으로 이해했다
```http
GET /api/users/me/reviews

Authorization : Bearer {accessToken}
```
- DB의 테이블명과 api 경로 설계는 아무런 연관성이 없다
- api가 표현할 자원은 유저의 리뷰이므로 리뷰 테이블의 이름(shop_review)과 무관하게 `/reviews`를 사용한다
- 서버의 데이터베이스 구조가 바뀌더라도(ex. shop_review 테이블이 store_reviews로 변경) api 경로는 `.../reviews`로 유지될 수 있어야 한다 → api의 추상화

<br/>

### 3️⃣ 미션 목록 조회
같은 api 경로 변수를 사용한다. 미션의 진행 여부는 쿼리 스트링의 값으로 식별한다
#### 진행 중
```http
GET /api/users/me/missions?status=in_progress

Authorization : Bearer {accessToken}
```

#### 진행 완료
```http
GET /api/users/me/missions?status=completed

Authorization : Bearer {accessToken}
```

<br/>

### 4️⃣ 미션 성공 누르기
```http
PATCH /api/users/me/missions/{missionId}

{
  "status": "completed"
}

Authorization : Bearer {accessToken}
```
- 미션의 상태만을 바꾸는 것이므로 리소스의 일부를 수정하는 PATCH를 메소드를 사용한다
- 어떤 유저의 어떤 미션인지를 구분하기 위해 경로 변수에 2개의 식별값을 사용한다
- 무엇을 어떻게 변경할지에 대한 정보를 바디에 담는다
- 이때 api 바디의 필드명은 DB 테이블 칼럼명과는 별개의 개념으로, 클라이언트가 이해하기 쉬운 직관적인 이름을 사용한다

<br/>

### 5️⃣ 회원 가입하기
```http
POST /auth/users

{
  "id" : "eno",
  "pw" : "yu1234",
  "name" : "김서현",
  "gender" : "female",
  "brithDate" : "2004-02-07",
  "address" : "경상북도 경산시 대학로 280"
}
```
- 기능을 위한 api와 인증을 위한 auth를 구분
- 요청 바디의 경우 각 타입에 맞는 값을 사용한다
- 만약 회원의 정보 안에 나이 정보 같은 것이 포함되어 있다면 정수 타입으로 보내는 것이 좋다

<br/>

### 💡 {userId}와 me의 구분
나의 정보를 조회하거나 관리하는 모든 API의 `{userId}`는 me로 바꾸는 것이 좋다

1. 의미의 명확성
- `GET /api/users/123` : 이 API는 **123번 ID를 가진 유저의 정보**를 의미합니다. 누구든 123번 유저의 정보를 볼 수 있다는 해석이 가능하다. 주로 관리자가 다른 유저를 조회하기 위해 사용한다
- `GET /api/users/me` : 이 API는 **현재 로그인한 사용자 본인의 정보**라는 의미를 명확하게 전달한다. 클라이언트는 자신의 ID가 몇 번인지 신경 쓸 필요 없이 해당 api를 호출해 자신의 정보를 받아올 수 있다

2. 보안 및 편의성
- me를 사용하면 서버는 Authorization 헤더에 담긴 토큰을 분석해서 자동으로 사용자를 식별한다
- 클라이언트의 편리함 : 프론트엔드 개발자는 로그인 후 받은 자신의 userId를 따로 저장하고 관리할 필요 없이 항상 `/users/me` 경로로만 api를 호출하면 되므로 코드가 간결해진다
- 서버의 보안 강화 : 서버 개발자는 `/users/me`로 들어온 요청에 대해서는 토큰 정보만으로 사용자를 특정하면 되므로 다른 사용자의 정보를 실수로 노출할 위험이 사라진다. 경로 변수로 받은 id와 토큰의 id가 일치하는지 검증하는 번거로운 로직이 필요 없어진다


