# π Club Mileage πͺ
### κΈ°μ  μ€ν
* Java 11
* Spring Boot
* JPA 


### μ€νλ°©λ²
λ£¨νΈ λλ ν λ¦¬μμ
```
./gradlew clean
./graldew bootJar
java -jar ./build/libs/mileage-0.0.1-SNAPSHOT.jar
```

# API

## ν¬μΈνΈ μ λ¦½ API (/events)

* λ¦¬λ·° λ±λ‘
```
curl -d '{
    "type": "REVIEW",
    "action": "ADD",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "μ’μμ!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}' -H "Content-Type: application/json" -X POST localhost:8080/events
```

* λ¦¬λ·° μμ 
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

* λ¦¬λ·° μ­μ 
```
curl -d '{
    "type": "REVIEW",
    "action": "DELETE",
    "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
    "content": "μ’μμ!",
    "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
    "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
    "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}' -H "Content-Type: application/json" -X POST localhost:8080/events
```

## ν¬μΈνΈ μ‘°ν API 
```
GET localhost:8080/points/{userId}

ex) localhost:8080/points/3ede0ef2-92b7-4817-a5f3-0c575361f745
```

### h2_console μ μ

`localhost:8080/h2_console`

<img width="404" alt="αα³αα³αα΅α«αα£αΊ 2022-07-13 αα©αα₯α« 1 45 40" src="https://user-images.githubusercontent.com/47476276/178547909-36943ec0-41b3-4392-8175-0f78c313cb80.png">

`JDBC URL: jdbc:h2:mem://localhost/~/testdb;MODE=MySQL`

### νμ€νΈ λ°μ΄ν°

* USER_ID1(userId): "3ede0ef2-92b7-4817-a5f3-0c575361f745"
* USER_ID2(userId): "ktde0ef2-92b7-4817-a5f3-0c575361f746"
* USER_ID3(userId): "acde0ef2-92b7-4817-a5f3-0c575361f746"

<br>

* PLACE_ID1(placeId): "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
* PLACE_ID2(placeId): "2e4baf1c-5acb-4efb-a1af-eddada31b00e"

μ νλ¦¬μΌμ΄μ μ€ν μ μ½μλλ μ΄κΈ°λ°μ΄ν°μλλ€. μ λ°μ΄ν°λ₯Ό νμ©νμ¬ ν¬μΈνΈ API νμ€νΈλ₯Ό ν  μ μμ΅λλ€.


