package triple.club.mileage.dto;

import lombok.Builder;
import lombok.Data;
import triple.club.mileage.domain.PointHistory;

import java.util.List;

@Data
@Builder
public class PointUserHistoryResponseDTO {
    String userId;
    long currPoint;
    List<PointHistoryDTO> pointHistoryDTOList;

    public static PointUserHistoryResponseDTO createDTO(String userId, long currPoint, List<PointHistoryDTO> pointHistoryDTOS) {
        return PointUserHistoryResponseDTO.builder()
                .userId(userId).currPoint(currPoint).pointHistoryDTOList(pointHistoryDTOS).build();
    }
}
