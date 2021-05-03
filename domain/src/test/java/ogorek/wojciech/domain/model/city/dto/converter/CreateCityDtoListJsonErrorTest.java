package ogorek.wojciech.domain.model.city.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.extension.city.dto.converter.CreateCityDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CreateCityDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateCityDtoListJsonErrorTest {
    private final CreateCityDtoListJsonConverter createCityDtoListJsonConverter;

    @Test
    @DisplayName("when json converter works with bad data type")
    public void test1(){
        //todo check it
//
//        var asd = createCityDtoJsonConverter.fromJson().orElseThrow();
//        System.out.println(asd);
//       assertThatThrownBy(() -> createCityDtoJsonConverter.fromJson().orElseThrow())
//               .isInstanceOf(AppConverterException.class);



    }
}
