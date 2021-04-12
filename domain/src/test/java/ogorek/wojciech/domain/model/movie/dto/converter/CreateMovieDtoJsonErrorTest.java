package ogorek.wojciech.domain.model.movie.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.movie.dto.converter.CreateMovieDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(CreateMovieDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateMovieDtoJsonErrorTest {

    private final CreateMovieDtoJsonConverter createMovieDtoJsonConverter;

    @Test
    @DisplayName("when create movie dto conversion throws exception")
    public void test1(){
        assertThatThrownBy(() -> createMovieDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageStartingWith("CONVERTER EXCEPTION: fromJson -> fileReader");

    }
}
