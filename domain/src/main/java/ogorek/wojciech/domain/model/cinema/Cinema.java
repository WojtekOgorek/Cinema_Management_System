package ogorek.wojciech.domain.model.cinema;

import lombok.*;
import ogorek.wojciech.domain.model.cinema.dto.GetCinemaDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Cinema {
    Long id;
    String name;
    Long cityId;

    public GetCinemaDto toGetCinemaDto() {
        return GetCinemaDto
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();
    }


}





