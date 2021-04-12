package ogorek.wojciech.domain.model.favourite.dto;

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
public class CreateFavDto {

    private Long userId;
    private Long movieId;

    public Favourite toFavourite(){
        return Favourite
                .builder()
                .userId(userId)
                .movieId(movieId)
                .addDate(LocalDateTime.now())
                .build();
    }
}

