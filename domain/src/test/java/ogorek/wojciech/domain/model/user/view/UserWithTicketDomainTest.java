package ogorek.wojciech.domain.model.user.view;

import ogorek.wojciech.domain.model.user.dto.GetUserWithTicketDto;
import ogorek.wojciech.domain.model.user.views.UserWithTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserWithTicketDomainTest {

    @Test
    @DisplayName("when conversion to get user with ticket dto is correct")
    public void test1(){

        var userId = 1L;
        var ticketId = 2L;

        var userWithTicket = UserWithTicket
                .builder()
                .userId(userId)
                .ticketId(ticketId)
                .build();

        var expectedUserWithTicket = GetUserWithTicketDto
                .builder()
                .userId(userId)
                .ticketId(ticketId)
                .build();

        var getUserWithTicket = userWithTicket.toGetUserWithTicketDto();

        assertThat(getUserWithTicket).isEqualTo(expectedUserWithTicket);
    }

    @Test
    @DisplayName("when user with ticket has all necessary properties")
    public void test2() {

        var userId = 1L;
        var ticketId = 2L;

        var userWithTicket = UserWithTicket
                .builder()
                .userId(userId)
                .ticketId(ticketId)
                .build();

        assertThat(userWithTicket).hasFieldOrProperty("userId");
        assertThat(userWithTicket).hasFieldOrProperty("ticketId");
    }

}
