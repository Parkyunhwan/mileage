# club mileage

### 기술 스택
* Java11
* Spring Boot
* JPA 
* H2 (MySQL)


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
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
    851d-4a50-bb07-9cc15cbdc332"],
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
