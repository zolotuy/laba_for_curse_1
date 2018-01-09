package serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLSerialization<T> implements Serialization<T> {
    @Override
    public void toFile(T t, String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        xmlMapper.writeValue(new File(filename), t);
    }

    @Override
    public T fromFile(String filename, Class<T> clases) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        T t = xmlMapper.readValue(new File(filename), clases);
        return t;
    }

    @Override
    public List<T> listFromFile(String filename,Class<T> tClass) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        TypeFactory typeFactory = xmlMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, tClass);
//        List<T> list = xmlMapper.readValue(new File(filename), new TypeReference<List<T>>() {
//        });
//        return list;
        return xmlMapper.readValue(new File(filename), collectionType);
    }

    @Override
    public void listToFile(List<T> list, String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
        xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        xmlMapper.writeValue(new File(filename), list);
    }
}
