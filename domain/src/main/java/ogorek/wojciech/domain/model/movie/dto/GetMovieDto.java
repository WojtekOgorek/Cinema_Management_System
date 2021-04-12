package ogorek.wojciech.domain.model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetMovieDto {
    private Long id;
    private String title;
    private String genre;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
