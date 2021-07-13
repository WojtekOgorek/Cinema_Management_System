package ogorek.wojciech.domain.model.user.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.user.dto.GetUserHistoryDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserHistory {
    private Long userId;
    private String cinemaName;
    private String movieTitle;
    private LocalDateTime dateTime;
    private BigDecimal price;

    public GetUserHistoryDto toGetUserHistoryDto(){
        return GetUserHistoryDto
                .builder()
                .userId(userId)
                .cinemaName(cinemaName)
                .movieTitle(movieTitle)
                .date_time(dateTime)
                .price(price)
                .build();
    }
}
