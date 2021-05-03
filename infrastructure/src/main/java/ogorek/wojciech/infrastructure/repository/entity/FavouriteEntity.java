package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.favourite.Favourite;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteEntity {

    private Long id;
    private Long userId;
    private Long movieId;
    private LocalDateTime addDate;

    public Favourite toFavourite(){
        return Favourite
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();
    }

    public FavouriteEntity fromFavourite(Favourite favourite){
        return FavouriteEntity
                .builder()
                .id(id)
                .userId(userId)
                .movieId(movieId)
                .addDate(addDate)
                .build();

    }
}
