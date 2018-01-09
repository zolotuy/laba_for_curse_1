package serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Group;
import model.Student;
import model.Subject;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by zolotuy on 02.01.2018.
 */
public class JSONSerializable<T> implements Serialization<T> {

    @Override
    public void toFile(T t, String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
       // objectMapper.writeValue(new File(filename), t);
        objectMapper.writeValue(new File(filename),objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(t));

    }

    @Override
    public T fromFile(String filename, Class<T> clases) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        T t = objectMapper.readValue(new File(filename), clases);
        return t;
    }

    @Override
    public List<T> listFromFile(String filename,Class<T> tClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, tClass);
//        List<T> list = objectMapper.readValue(new File(filename), new TypeReference<List<T>>() {
//        });
//        return list;
        return objectMapper.readValue(new File(filename), collectionType);
    }

    @Override
    public void listToFile(List<T> list, String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.writeValue(new File(filename), list);
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
//        objectMapper.writeValue(new File(filename),objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }

}
