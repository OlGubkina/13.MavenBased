package examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AuthorizationPage;
import pages.HomePage;
import utils.PropertyReader;
import java.util.concurrent.TimeUnit;

public class HomeWork15Test extends TestBase {
    /** ДЗ_lesson_15. Регистрация нового пользователя */
    @Test
    public void homeWork15() {
        // WebDriverManager.chromedriver().setup(); //1.Подключить хром драйвер: прописать путь к драйверу через менедж тул
        // WebDriver driver = new ChromeDriver();   //2.Создать новый драйвер
        driver.get(PropertyReader.BASEURL);         //3.Переход на стартовую страницу

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Таймаут на прогрузку элементов 10 сек
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);// Передаём управление драйвера на homePage
        homePage.checkOnPage();
        homePage.clickSignIn(); // Переход на страницу авторизации

        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.checkOnPage();
        authorizationPage.createAnAccountButtonClick();
        authorizationPage.fillTheForm();
        authorizationPage.checkTheRegIsOk();
        driver.close();
    }
}
