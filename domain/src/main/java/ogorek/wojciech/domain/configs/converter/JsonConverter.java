package ogorek.wojciech.domain.configs.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


public class JsonConverter<T> {
    private final String jsonFilename;
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private final Gson gson =
            new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateDeserializer())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateSerializer())
                    .setPrettyPrinting()
                    .create();

    public JsonConverter(String jsonFilename) {
        this.jsonFilename = jsonFilename;
    }


    public void toJson(final T element) {
        if (Objects.isNull(element)) {
            throw new AppConverterException("CONVERTER EXCEPTION: toJson is null ");
        }
        try (FileWriter fileWriter = new FileWriter(jsonFilename)) {
            gson.toJson(element, fileWriter);
        } catch (Exception e) {
            throw new AppConverterException("CONVERTER EXCEPTION: toJson -> fileWriter " + e.getMessage());
        }
    }

    public Optional<T> fromJson() {
        try (FileReader fileReader = new FileReader(jsonFilename)) {
            return Optional.of(gson.fromJson(fileReader, type));
        } catch (Exception e) {
            throw new AppConverterException("CONVERTER EXCEPTION: fromJson -> fileReader " + e.getMessage());
        }
    }

}

