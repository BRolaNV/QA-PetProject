package tests.reqres;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class APIReader {

    private static final String API_KEY_ENV = "REQRES_API_KEY";
    private static final String API_KEY_PROPERTY = "api.key";
    private static final String CONFIG_FILE = "config.properties";

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = APIReader.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not load " + CONFIG_FILE, e);
        }
    }

    public static String getApiKey() {
        String envKey = System.getenv(API_KEY_ENV);
        if (envKey != null && !envKey.isBlank()) {
            return envKey;
        }

        String fileKey = properties.getProperty(API_KEY_PROPERTY);
        if (fileKey != null && !fileKey.isBlank()) {
            return fileKey;
        }

        throw new IllegalStateException(
                "API key not found. Set " + API_KEY_ENV + " env variable " +
                        "or add " + API_KEY_PROPERTY + " to " + CONFIG_FILE
        );
    }
}