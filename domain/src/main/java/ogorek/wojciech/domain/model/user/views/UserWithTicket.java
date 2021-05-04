package ogorek.wojciech.domain.model.user.views;

import lombok.*;
import ogorek.wojciech.domain.model.user.dto.GetUserWithTicketDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserWithTicket {
    private Long userId;
    private Long ticketId;

    public GetUserWithTicketDto toGetUserWithTicketDto(){
        return GetUserWithTicketDto
                .builder()
                .userId(userId)
                .ticketId(ticketId)
                .build();
    }
}
