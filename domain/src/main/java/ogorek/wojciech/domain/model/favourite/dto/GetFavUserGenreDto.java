package ogorek.wojciech.domain.model.favourite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFavUserGenreDto {
    private Long id;
    private Long userId;
    private String genre;
}
