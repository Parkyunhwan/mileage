package triple.club.mileage.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import triple.club.mileage.exception.errorcode.ErrorCode;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {
    private final ErrorCode errorCode;
}
