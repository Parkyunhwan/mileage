package triple.club.mileage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private Object result;

    public static ResponseEntity<ApiResponse> of(String message, Object result) {
        ApiResponse apiResponse = ApiResponse.builder()
                .message(message)
                .result(result).build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
