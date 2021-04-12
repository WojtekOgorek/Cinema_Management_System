package ogorek.wojciech.domain.model.favourite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFavDto {
    private Long id;
    private Long userId;
    private Long movieId;
    private LocalDateTime addDate;
}
