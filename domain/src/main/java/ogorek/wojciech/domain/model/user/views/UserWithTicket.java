package ogorek.wojciech.domain.model.user.views;

import lombok.*;
import ogorek.wojciech.domain.model.user.dto.GetUserWithTicketDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class UserWithTicket {
    Long userId;
    Long ticketId;

    public GetUserWithTicketDto toGetUserWithTicketDto(){
        return GetUserWithTicketDto
                .builder()
                .userId(userId)
                .ticketId(ticketId)
                .build();
    }
}
