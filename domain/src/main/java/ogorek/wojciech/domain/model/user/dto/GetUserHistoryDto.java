package ogorek.wojciech.domain.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserHistoryDto {
    private Long userId;
    private String cinemaName;
    private String movieTitle;
    private LocalDateTime date_time;
    private BigDecimal price;
}
