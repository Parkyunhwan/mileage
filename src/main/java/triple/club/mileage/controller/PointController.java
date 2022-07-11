package triple.club.mileage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import triple.club.mileage.domain.PointHistory;
import triple.club.mileage.domain.User;
import triple.club.mileage.dto.*;
import triple.club.mileage.exception.RestApiException;
import triple.club.mileage.exception.errorcode.UserErrorCode;
import triple.club.mileage.repository.UserRepository;
import triple.club.mileage.service.event.EventService;
import triple.club.mileage.service.point.PointHistoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointHistoryService pointHistoryService;
    private final UserRepository userRepository;

    @PostMapping("/points/{userId}")
    public ResponseEntity<ApiResponse> actionEvent(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RestApiException(UserErrorCode.NONEXIST_USER));
        List<PointHistoryDTO> pointHistoryDTOS = pointHistoryService.retrieveAllUserHistory(userId);
        PointUserHistoryResponseDTO dto = PointUserHistoryResponseDTO.createDTO(userId, user.getPointScore(), pointHistoryDTOS);
        return ApiResponse.of("특정 유저의 포인트 정보 조회", dto);
    }
}
