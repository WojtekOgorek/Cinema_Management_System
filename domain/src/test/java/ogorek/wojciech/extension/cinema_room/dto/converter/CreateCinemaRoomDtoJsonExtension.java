package ogorek.wojciech.extension.cinema_room.dto.converter;

import ogorek.wojciech.domain.model.cinema.dto.converter.CreateCinemaDtoJsonConverter;
import ogorek.wojciech.domain.model.cinema_room.dto.converter.CreateCinemaRoomDtoJsonConverter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateCinemaRoomDtoJsonExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CreateCinemaRoomDtoJsonConverter.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var filename = "C:\\Work\\KmPrograms\\Java\\Coding\\Projects_GIT\\Cinema_Management_System\\domain\\src\\test\\resources\\CreateCinemaRoomDto-1.json";
        return new CreateCinemaRoomDtoJsonConverter(filename);
    }
}
