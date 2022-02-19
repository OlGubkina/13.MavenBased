package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AuthorizationPage;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {
    private static WebDriver driver; // Без static не работал driver.close

    @BeforeAll
    // Значение браузера лежит в файле config.properties
    public static void setUp() {
        String browser = PropertyReader.BROWSER;
        switch (browser) {
            case ("chrome") : {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
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

/**   Домашка
 *      Открыть сайт
 *      нажать контакт кнопку
 *      проверить переход
 *
 *      Вопросы
 *      Все тесты должны быть в одном классе? как организовано хранение кейсов
 *
 */

@Test
    public void UseDriverManager () throws InterruptedException {
        driver.get("http://automationpractice.com/");
        Assertions.assertTrue(driver.getCurrentUrl().contains("http://automationpractice.com")); // Нужно?
        Thread.sleep(1300);
        WebElement contactButton = driver.findElement(By.xpath("//a[@title='Contact us']"));
        if (contactButton.isDisplayed())
            contactButton.click();
            Thread.sleep(1300); // Задержка в мили секундах
        assertTrue(driver.getCurrentUrl().contains("contact"));



        /** Клик по [Sign in] + проверка перехода на страницу авторизации
        WebElement signInButton = driver.findElement(By.xpath("//a[@class=\"login\"]"));
        if (signInButton.isDisplayed())
            signInButton.click();
        Thread.sleep(300); // Задержка в мили секундах
        assertTrue(driver.getCurrentUrl().contains("authentication"));
         */
         }

    /* Тест на авторизацию пользователя с заданным лог\пасс
    @Test
    public void authorizeTest() throws InterruptedException {
        AuthorizationPage authorizationPage = AuthorizationPage.navigeteHere(driver);
        authorizationPage.doAuthorize();
        WebElement navigationSpan = driver.findElement(By.xpath("//span[@class='navigation_page']"));
        assertEquals(navigationSpan.getText(),"My account");
        Thread.sleep(1500);
    }

     */



    @AfterAll
    public static void tearDown() {
        driver.close(); // После теста закрываем окно браузера
    }
}
