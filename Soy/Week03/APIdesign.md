# 홈 화면 API

## 안 읽은 알림 여부, 사용자 달성 미션 개수 조회

### API endpoint
GET /home

### Request Body
필요 없음

### Request Header
Authorization : Bearer {accessToken}
Accept: application/json

### Query String
필요 없음

## 드롭다운으로 선택할 수 있는 지역 목록 조회

### API endpoint
GET /address-list

### Request Body
필요 없음

### Request Header
Accept: application/json

### Query String
city={'서울특별시'}&district={'성북구'}

## 사용자가 도전 중이지 않은 해당 지역의 미션 목 조회

### API endpoint
GET /home/missions

### Request Body
필요 없음

### Request Header
Authorization : Bearer {accessToken}
Accept: application/json

### Query String
city={'서울특별시'}&district={'성북구'}&size={6}&last-mission-id={24534}

# 마이 페이지 리뷰 작성

## 리뷰 작성

### API endpoint
POST /users/{user-id}/reviews

### Request Body
--{boundaryString}
Content-Disposition: form-data; name={"rating"}

{5}
--{boundaryString}
Content-Disposition: form-data; name={"content"}

{"정말 맛있어요"}
--{boundaryString}
Content-Disposition: form-data; name={"photos"}; filename={"2025100301.jpg"}
Content-Type: image/jpeg

{[JPG 파일의 이진 데이터(Binary Data)]}
--{boundaryString}
Content-Disposition: form-data; name={"photos"}; filename={"2025100307.jpg"}
Content-Type: image/jpeg

{[JPG 파일의 이진 데이터(Binary Data)]}
--{boundaryString}--

### Request Header
Authorization : Bearer {accessToken}
Accept: application/json
Content-Type: multipart/form-data; boundary={boundaryString}

### Query String
rest-id={334}

# 미션 목록 조회

## 미션 목록 조회

### API endpoint
GET /users/{user-id}/missions

### Request Body
필요 없음

### Request Header
Authorization : Bearer {accessToken}
Accept: application/json

### Query String
complete={true}&size={6}&last-mission-id={24534}
