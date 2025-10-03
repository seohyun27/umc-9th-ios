### 회원가입 (소셜 로그인 제외)
**API Endpoint** : POST /auth/users/signup
    
**Request Header** : Content-Type: application/json
    
**Request Body** 
  ```json
    { 
          "name" : "김세은",
          "gender" : 1, //남자:0, 여자:1
          "birthday" : "2003-12-06",
          "address" : "경상북도 경산시"
    }
  ```
**query String** :  없음.
    
**Path variable** : 없음. (사용자 식별자 존재x)

-------------------------
### 홈화면
**API Endpoint** : GET /users/{userId}/missions
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body**  : X.

**query String** :  /users/{userId}/missions?view=home
    
**Path variable** : {userId}

-------------------------

### 마이페이지 리뷰 작성
**API Endpoint** : POST /missions/{missionId}/reviews --
    
**Request Header** : Authorization : Bearer {accessToken}
    
**Request Body**  : X.

**query String** :  /users/{userId}/missions?view=home
    
**Path variable** : {userId}

-------------------------
