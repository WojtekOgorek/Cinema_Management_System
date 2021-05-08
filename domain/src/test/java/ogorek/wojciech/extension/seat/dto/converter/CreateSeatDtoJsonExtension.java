package ogorek.wojciech.extension.seat.dto.converter;

import ogorek.wojciech.domain.model.seat.dto.converter.CreateSeatDtoJsonConverter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateSeatDtoJsonExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CreateSeatDtoJsonConverter.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var filename = "C:\\Work\\KmPrograms\\Java\\Coding\\Projects_GIT\\Cinema_Management_System\\domain\\src\\test\\resources\\CreateSeatDto-1.json";
        return new CreateSeatDtoJsonConverter(filename);
    }
}
