package ogorek.wojciech.service.services.cinema;

import ogorek.wojciech.domain.model.city.repository.CityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
     private CityService cityService;

    @Test
    @DisplayName("when finding all cities is correct")
    public void test1(){

    }
}
