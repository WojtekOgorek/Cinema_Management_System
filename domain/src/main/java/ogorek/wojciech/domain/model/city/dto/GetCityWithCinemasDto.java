package ogorek.wojciech.domain.model.city.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class GetCityWithCinemasDto {
    private Long cityId;
    private Long cinemaId;
}
