package ogorek.wojciech.domain.model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetCinemaDto {
    private Long id;
    private String name;
    private Long cityId;
}
