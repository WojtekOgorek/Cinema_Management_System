package ogorek.wojciech.domain.model.user.view;

import ogorek.wojciech.domain.model.user.dto.GetUserHistoryDto;
import ogorek.wojciech.domain.model.user.views.UserHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserHistoryDomainTest {

    @Test
    @DisplayName("when conversion to get user history dto is correct")
    public void test1(){

        var userId = 1L;
        var cinemaName = "Cinema";
        var movieTitle = "Title";
        var date_time = LocalDateTime.of(2021,1,1,12,15);
        var price = new BigDecimal("20");

        var userHistory = UserHistory
                .builder()
                .userId(userId)
                .cinemaName(cinemaName)
                .movieTitle(movieTitle)
                .date_time(date_time)
                .price(price)
                .build();

        var expectedUserHistory = GetUserHistoryDto
                .builder()
                .userId(userId)
                .cinemaName(cinemaName)
                .movieTitle(movieTitle)
                .date_time(date_time)
                .price(price)
                .build();

        var getUserHistory = userHistory.toGetUserHistoryDto();

        assertThat(getUserHistory).isEqualTo(expectedUserHistory);
    }

    @Test
    @DisplayName("when user history has all necessary properties")
    public void test2() {

        var userId = 1L;
        var cinemaName = "Cinema";
        var movieTitle = "Title";
        var date_time = LocalDateTime.of(2021, 1, 1, 12, 15);
        var price = new BigDecimal("20");

        var userHistory = UserHistory
                .builder()
                .userId(userId)
                .cinemaName(cinemaName)
                .movieTitle(movieTitle)
                .date_time(date_time)
                .price(price)
                .build();

        assertThat(userHistory).hasFieldOrProperty("userId");
        assertThat(userHistory).hasFieldOrProperty("cinemaName");
        assertThat(userHistory).hasFieldOrProperty("movieTitle");
        assertThat(userHistory).hasFieldOrProperty("date_time");
        assertThat(userHistory).hasFieldOrProperty("price");
    }
}
