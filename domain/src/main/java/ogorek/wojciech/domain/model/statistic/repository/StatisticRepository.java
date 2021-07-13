package ogorek.wojciech.domain.model.statistic.repository;

import ogorek.wojciech.domain.model.statistic.*;

import java.util.List;
import java.util.Optional;
public interface StatisticRepository {
    Optional<MostPopularCity> findMostPopularCity();
    List<MostPopularMovieInCities> findMostPopularMovieInCity();
    List<MostPopularGenreInCities> findMostPopularGenreInCity();
    List<AvgPricePerUserInCities> findAvgPricePerUserInCities();
    List<TotalPriceSumByCities> findTotalPriceSumByCities();
    List<MostPopularDayByCities> findMostPopularDayByCities();
    List<MostPopularTicketTypeInCities> findMostPopularTicketByCities();
}
