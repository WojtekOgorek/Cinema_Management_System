package ogorek.wojciech.domain.model.favourite.views;

import lombok.*;
import ogorek.wojciech.domain.model.favourite.dto.GetFavUserGenreDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FavouriteUserGenre {
    private Long id;
    private Long userId;
    private String genre;

    public GetFavUserGenreDto toGetFavUserGenreDto(){
        return GetFavUserGenreDto
                .builder()
                .id(id)
                .userId(userId)
                .genre(genre)
                .build();
    }
}
