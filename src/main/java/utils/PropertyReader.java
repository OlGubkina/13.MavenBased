package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    private static final String CONFIG_PATH = "config.properties";
    private static final Properties prop = initProperties();

    public static final String BROWSER = prop.getProperty("browser");
    public static final String BASEURL = prop.getProperty("baseUrl"); //? public???

    private PropertyReader() {}

    public static Properties getProperties() {return prop;}

    private static Properties initProperties() {
        Properties prop = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(CONFIG_PATH);
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Couldn`t load properties" + CONFIG_PATH);
        }
        return prop;
    }
}
