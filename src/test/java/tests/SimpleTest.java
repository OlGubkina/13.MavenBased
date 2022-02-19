package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {
    @Test
    public void sumTest() {assertTrue(true, "Message if condition = false");
    }

    @Test
    public void readTextFromResourcesTest() {
        String filePath = "attachments/text.txt";
        String text = "";
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            text = sb.toString();
            reader.close();
        } catch (IOException ex) {ex.printStackTrace();}
        assertTrue(text.contains("random text"), text + "Is no what we expected");
    }


    // works with JAR
    @Test
    public void simplifiedResourceReader() {
        String filePath = "attachments/text.txt";
        String text = "";
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
            text = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        } catch (IOException e) {e.printStackTrace();}
        assertTrue(text.contains("random text"), text + "is no what we expected");
    }


}