package ogorek.wojciech.domain.model.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//todo czy tu np tylko wystarczy ticketid
public class GetUserWithTicketDto {
   private Long userId;
   private Long ticketId;
}
