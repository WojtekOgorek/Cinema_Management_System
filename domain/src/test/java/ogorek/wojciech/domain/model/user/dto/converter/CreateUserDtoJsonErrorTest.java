package ogorek.wojciech.domain.model.user.dto.converter;

import lombok.RequiredArgsConstructor;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import ogorek.wojciech.extension.user.dto.converter.CreateUserDtoJsonErrorExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(CreateUserDtoJsonErrorExtension.class)
@RequiredArgsConstructor
public class CreateUserDtoJsonErrorTest {

    private final CreateUserDtoJsonConverter createUserDtoJsonConverter;

    @Test
    @DisplayName("when create user dto conversion from json throws exception")
    public void test1(){

        assertThatThrownBy(() -> createUserDtoJsonConverter.fromJson().orElseThrow())
                .isInstanceOf(AppConverterException.class)
                .hasMessageContaining("IllegalStateException");

    }
}
