package serialization;

import java.io.IOException;
import java.util.List;

/**
 * Created by zolotuy on 02.01.2018.
 */
public interface Serialization<T> {
    public void toFile(T t, String filename) throws IOException;
    public T fromFile(String filename,Class<T> clases) throws IOException;
    public List<T> listFromFile(String filename,Class<T> tClass) throws IOException;
    public void listToFile(List<T> list,String filename) throws IOException;
}
