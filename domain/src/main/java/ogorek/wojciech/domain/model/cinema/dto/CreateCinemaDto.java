package ogorek.wojciech.domain.model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema.Cinema;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCinemaDto {
    private String name;
    private Long cityId;

    public Cinema toCinema() {
        return Cinema
                .builder()
                .name(name)
                .cityId(cityId)
                .build();
    }
}
