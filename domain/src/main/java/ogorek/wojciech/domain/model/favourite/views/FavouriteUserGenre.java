package ogorek.wojciech.domain.model.favourite.views;

import lombok.*;
import ogorek.wojciech.domain.model.favourite.dto.GetFavUserGenreDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class FavouriteUserGenre {
    Long id;
    Long userId;
    String genre;

    public GetFavUserGenreDto toGetFavUserGenreDto(){
        return GetFavUserGenreDto
                .builder()
                .id(id)
                .userId(userId)
                .genre(genre)
                .build();
    }
}
