package ogorek.wojciech.service.services.cinema;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.validator.Validator;
import ogorek.wojciech.domain.model.city.City;
import ogorek.wojciech.domain.model.city.dto.CreateCityDto;
import ogorek.wojciech.domain.model.city.dto.GetCityDto;
import ogorek.wojciech.domain.model.city.dto.GetCityWithCinemasDto;
import ogorek.wojciech.domain.model.city.dto.validator.CreateCityDtoValidator;
import ogorek.wojciech.domain.model.city.repository.CityRepository;
import ogorek.wojciech.domain.model.city.views.CityWithCinemas;
import ogorek.wojciech.domain.model.statistic.*;
import ogorek.wojciech.domain.model.statistic.dto.*;
import ogorek.wojciech.domain.model.statistic.repository.StatisticRepository;
import ogorek.wojciech.service.services.exceptions.AppServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final StatisticRepository statisticRepository;


    public List<GetCityDto> findAllCities() {
        return cityRepository
                .findAll()
                .stream()
                .map(City::toGetCityDto)
                .collect(Collectors.toList());
    }

    public GetCityDto findCityById(Long id){
        return cityRepository
                .findById(id)
                .map(City::toGetCityDto)
                .orElseThrow(() -> new AppServiceException("..."));
    }

    public GetCityDto addCity(CreateCityDto createCityDto){
        Validator.validate(new CreateCityDtoValidator(), createCityDto);
        var city = createCityDto.toCity();
        return cityRepository
                .add(city)
                .map(City::toGetCityDto)
                .orElseThrow();
    }


    public GetCityDto updateCity(CreateCityDto createCityDto){
        Validator.validate(new CreateCityDtoValidator(), createCityDto);
        var city = createCityDto.toCity();
        return cityRepository
                .update(city)
                .map(City::toGetCityDto)
                .orElseThrow();
    }

    public GetCityDto deleteCity(Long id){
        return cityRepository
                .delete(id)
                .map(City::toGetCityDto)
                .orElseThrow();
    }

    public boolean deleteAllCities(){
        return cityRepository
                .deleteAll();
    }

    public GetCityDto findCityByName(String name){
        return cityRepository
                .findCityByName(name)
                .map(City::toGetCityDto)
                .orElseThrow();
    }

    public List<GetCityWithCinemasDto> findCitiesWithCinemas(){
        return cityRepository
                .citiesWithCinemas()
                .stream()
                .map(CityWithCinemas::getCityWithCinemasDto)
                .collect(Collectors.toList());
    }

    public List<GetCityWithCinemasDto> findCityWithCinemasByName(String name){
        return cityRepository
                .citiesWithCinemas()
                .stream()
                .map(CityWithCinemas::getCityWithCinemasDto)
                .collect(Collectors.toList());
    }

    //--------------------- STATISTICS MOST POPULAR CITY ---------------------------------------

    
    public GetMostPopularCityDto findMostPopularCity(){
        return statisticRepository
                .findMostPopularCity()
                .map(MostPopularCity::toGetMostPopularCityDto)
                .orElseThrow();

    }

    public List<GetMostPopularMovieInCitiesDto> findMostPopularMoviesInCities(){
        return statisticRepository
                .findMostPopularMovieInCity()
                .stream()
                .map(MostPopularMovieInCities::toGetMostPopularMovieInCitiesDto)
                .collect(Collectors.toList());

    }

    public List<GetMostPopularGenreInCitiesDto> findMostPopularGenreInCities(){
        return statisticRepository
                .findMostPopularGenreInCity()
                .stream()
                .map(MostPopularGenreInCities::toGetMostPopularGenreInCitiesDto)
                .collect(Collectors.toList());
    }

    public List<GetAvgPricePerUserInCitiesDto> findAvgPricePerUserInCities(){
        return statisticRepository
                .findAvgPricePerUserInCities()
                .stream()
                .map(AvgPricePerUserInCities)
                .collect(Collectors.toList());
    }

    public List<GetTotalPriceSumByCitiesDto> findTotalPriceSumByCities(){
        return statisticRepository
                .findTotalPriceSumByCities()
                .stream()
                .map(TotalPriceSumByCities::toGetTotalPriceSumByCitiesDto)
                .collect(Collectors.toList());
    }

    public List<GetMostPopularDayByCitiesDto> findMostPopularDayByCities(){
        return statisticRepository
                .findMostPopularDayByCities()
                .stream()
                .map(MostPopularDayByCities::toGetMostPopularDayByCitiesDto)
                .collect(Collectors.toList());
    }

    public List<GetMostPopularTicketTypeInCitiesDto> findMostPopularTicketType(){
        return statisticRepository
                .findMostPopularTicketByCities()
                .stream()
                .map(MostPopularTicketTypeInCities::toGetMostPopularTicketTypeInCitiesDto)
                .collect(Collectors.toList());
    }
}
