package ogorek.wojciech.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogorek.wojciech.domain.model.cinema.Cinema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaEntity {
    private Long id;
    private String name;
    private Long cityId;

    public Cinema toCinema(){
        return Cinema
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();
    }

    public CinemaEntity fromCinema(Cinema cinema){
        return CinemaEntity
                .builder()
                .id(id)
                .name(name)
                .cityId(cityId)
                .build();
    }
}
