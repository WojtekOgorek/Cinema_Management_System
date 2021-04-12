package ogorek.wojciech.domain.model.favourite;

import lombok.*;
import ogorek.wojciech.domain.model.favourite.dto.GetFavDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Favourite {
    Long id;
    Long userId;
    Long movieId;
    LocalDateTime addDate;

    public GetFavDto toGetFavDto(){
        return GetFavDto
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();
    }
}
