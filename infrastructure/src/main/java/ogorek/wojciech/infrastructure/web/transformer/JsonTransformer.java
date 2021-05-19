package ogorek.wojciech.infrastructure.web.transformer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import ogorek.wojciech.domain.configs.converter.AppConverterException;
import org.springframework.stereotype.Component;
import spark.ResponseTransformer;

import java.lang.reflect.Type;

@Component
public class JsonTransformer implements ResponseTransformer {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }

    public <T> T fromJson(String json, Class<T> t) {
        return gson.fromJson(json, t);
    }

}
