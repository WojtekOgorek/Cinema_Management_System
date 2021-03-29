package ogorek.wojciech.domain.configs.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class JsonConverter<T> {
    private final String jsonFilename;
    private final Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private final Gson gson =
            new GsonBuilder()
            .registerTypeAdapter(type, new LocalDateSerializer())
            .registerTypeAdapter(type, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();


    public void toJson(final T element){
        if(Objects.isNull(element)){
            throw new AppConverterException("CONVERTER EXCEPTION: toJson ");
        }
        try(FileWriter fileWriter = new FileWriter(jsonFilename)){
            gson.toJson(element, fileWriter);
        }catch (Exception e){
            throw new AppConverterException("CONVERTER EXCEPTION: toJson -> fileWriter " + e.getMessage());
        }
    }

    public Optional<T> fromJson(){
        try(FileReader fileReader = new FileReader(jsonFilename)){
            return Optional.of(gson.fromJson(fileReader, type));
        }catch (Exception e){
            throw new AppConverterException("CONVERTER EXCEPTION: fromJson -> fileReader " + e.getMessage());
        }
    }

}
