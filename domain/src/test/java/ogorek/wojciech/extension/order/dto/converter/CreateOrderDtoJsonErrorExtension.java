package ogorek.wojciech.extension.order.dto.converter;

import ogorek.wojciech.domain.model.order.dto.converter.CreateOrderDtoJsonConverter;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateOrderDtoJsonErrorExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CreateOrderDtoJsonConverter.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var filename = "C:\\Work\\KmPrograms\\Java\\Coding\\Projects_GIT\\Cinema_Management_System\\domain\\src\\test\\resources\\CreateOrderDto-2.json";
        return new CreateOrderDtoJsonConverter(filename);
    }
}
