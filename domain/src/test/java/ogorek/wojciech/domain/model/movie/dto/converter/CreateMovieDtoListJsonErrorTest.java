package ogorek.wojciech.domain.model.movie.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.movie.dto.converter.CreateMovieDtoListJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateMovieDtoListJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateMovieDtoListJsonErrorTest {

    private final CreateMovieDtoListJsonConverter createMovieDtoListJsonConverter;

    @Test
    @DisplayName("when create movie dto conversion throws exception")
    public void test1(){
        assertThatThrownBy(() -> createMovieDtoListJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageStartingWith("CONVERTER EXCEPTION: fromJson -> fileReader");

    }
}
