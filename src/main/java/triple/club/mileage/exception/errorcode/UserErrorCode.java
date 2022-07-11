package triple.club.mileage.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    NONEXIST_USER(HttpStatus.BAD_REQUEST, "등록되지 않은 사용자입니다."),
    NONEXIST_PLACE(HttpStatus.BAD_REQUEST, "등록되지 않은 장소입니다."),
    NONEXIST_REVIEW(HttpStatus.BAD_REQUEST, "등록되지 않은 리뷰입니다."),
    ALREADY_REGISTER_REVIEW(HttpStatus.BAD_REQUEST, "이미 등록된 reviewId 입니다."),
    ONE_USER_ONE_REVIEW(HttpStatus.BAD_REQUEST, "하나의 유저가 한 장소에 2개이상의 리뷰를 작성할 수 없습니다."),
    NOT_PLACE_REVIEW(HttpStatus.BAD_REQUEST, "해당 장소의 리뷰가 아닙니다."),
    NOT_USER_REVIEW(HttpStatus.BAD_REQUEST, "해당 사용자의 리뷰가 아닙니다.");


    private final HttpStatus httpStatus;
    private final String message;
}