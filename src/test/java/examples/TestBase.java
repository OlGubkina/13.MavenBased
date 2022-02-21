package examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;

    @BeforeEach
    // Значение браузера (browser=chrome) лежит в файле config.properties
    public void setUp() {
        String browser = PropertyReader.BROWSER;
        switch (browser) {
            case ("chrome") : {
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                //chromeOptions.addArguments("version");
                // посмотреть в гугле опции !!!

                driver = new ChromeDriver(chromeOptions);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                break;
            }
            case ("firefox") : {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            default: throw new InvalidArgumentException("Chrome or Firefox needed");
        }
    }

    @AfterEach
    public void tearDown() {
        driver.close(); // После теста закрываем окно браузера
    }
}
