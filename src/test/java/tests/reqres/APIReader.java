package tests.reqres;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = APIReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }
}