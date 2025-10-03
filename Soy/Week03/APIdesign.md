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

