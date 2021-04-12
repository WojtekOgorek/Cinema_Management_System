package ogorek.wojciech.domain.model.city.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CreateCityDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCityDtoJsonErrorTest {
    private final CreateCityDtoJsonConverter createCityDtoJsonConverter;

    @Test
    @DisplayName("when json converter works with bad data type")
    public void test1(){
//
//        var asd = createCityDtoJsonConverter.fromJson().orElseThrow();
//        System.out.println(asd);
//       assertThatThrownBy(() -> createCityDtoJsonConverter.fromJson().orElseThrow())
//               .isInstanceOf(AppConverterException.class);



    }
}
