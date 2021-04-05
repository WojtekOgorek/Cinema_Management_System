package ogorek.wojciech.extension.city.dto.converter;

import ogorek.wojciech.domain.model.city.dto.converter.CreateCityDtoJsonConverter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateCityDtoJsonErrorExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CreateCityDtoJsonConverter.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var filename = "C:\\Work\\KmPrograms\\Java\\Coding\\Projects_GIT\\Cinema_Management_System\\domain\\src\\test\\resources\\createCitiesDto-2.json";
        return new CreateCityDtoJsonConverter(filename);
    }
}
