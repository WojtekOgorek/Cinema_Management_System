package ogorek.wojciech.domain.model.user.views;

import lombok.*;
import ogorek.wojciech.domain.model.user.dto.GetUserHistoryDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserHistory {
    Long userId;
    String cinemaName;
    String movieTitle;
    LocalDateTime date_time;
    BigDecimal price;

    public GetUserHistoryDto toGetUserHistoryDto(){
        return GetUserHistoryDto
                .builder()
                .userId(userId)
                .cinemaName(cinemaName)
                .movieTitle(movieTitle)
                .date_time(date_time)
                .price(price)
                .build();
    }
}
