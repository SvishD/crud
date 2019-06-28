package app.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    public static String get(String name)  {
        Properties properties = new Properties();
        String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("config.properties")).getPath();

        try(InputStream in = new FileInputStream(path) ) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }
}
