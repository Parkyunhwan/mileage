# 🏟 Club Mileage 🪂
### 기술 스택
* Java11
* Spring Boot
* JPA 


### 실행방법
루트 디렉토리에서
```
./gradlew clean
./graldew bootJar
java -jar ./build/libs/mileage-0.0.1-SNAPSHOT.jar
```

# API

## 포인트 적립 API (/events)

* 리뷰 등록
```
curl -d '{
    "type": "REVIEW",
    "action": "ADD",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "좋아요!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}' -H "Content-Type: application/json" -X POST localhost:8080/events
```

* 리뷰 수정
```
curl -d '{
    "type": "REVIEW",
    "action": "MOD",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}' -H "Content-Type: application/json" -X POST localhost:8080/events
```

* 리뷰 삭제
```
curl -d '{
    "type": "REVIEW",
    "action": "DELETE",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "좋아요!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}' -H "Content-Type: application/json" -X POST localhost:8080/events
```

## 포인트 조회 API 
```
GET localhost:8080/points/{userId}

ex) localhost:8080/points/3ede0ef2-92b7-4817-a5f3-0c575361f745
```

### h2_console 접속

`localhost:8080/h2_console`

<img width="404" alt="스크린샷 2022-07-13 오전 1 45 40" src="https://user-images.githubusercontent.com/47476276/178547909-36943ec0-41b3-4392-8175-0f78c313cb80.png">

`JDBC URL: jdbc:h2:mem://localhost/~/testdb;MODE=MySQL`

### 테스트 데이터

* USER_ID1(userId): "3ede0ef2-92b7-4817-a5f3-0c575361f745"
* USER_ID2(userId): "ktde0ef2-92b7-4817-a5f3-0c575361f746"
* USER_ID3(userId): "acde0ef2-92b7-4817-a5f3-0c575361f746"

<br>

* PLACE_ID1(placeId): "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
* PLACE_ID2(placeId): "2e4baf1c-5acb-4efb-a1af-eddada31b00e"

애플리케이션 실행 시 삽입되는 초기데이터입니다. 위 데이터를 활용하여 포인트 API 테스트를 할 수 있습니다.


