package examples;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AuthorizationPage;
import pages.HomePage;
import pages.MyAccountPage;
import utils.PropertyReader;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest extends TestBase {
    /** ДЗ_lesson_14. Открыть сайт, нажать контакт кнопку, проверить переход на страницу контакты */
    @Test
    public void UseDriverManager () {
        driver.get(PropertyReader.BASEURL); //driver.get("http://automationpractice.com/");
        HomePage homePage = new HomePage(driver);
        homePage.checkOnPage(); // на главной странице title = My Store
        WebElement contactButton = driver.findElement(By.xpath("//a[@title='Contact us']"));
        if (contactButton.isDisplayed())
            contactButton.click();
        assertTrue(driver.getCurrentUrl().contains("contact"));
    }

        /* Клик по [Sign in] + проверка перехода на страницу авторизации
        WebElement signInButton = driver.findElement(By.xpath("//a[@class=\"login\"]"));
        if (signInButton.isDisplayed())
            signInButton.click();
        Thread.sleep(300); // Задержка в мили секундах
        assertTrue(driver.getCurrentUrl().contains("authentication"));
        */

    // Авторизация пользователя с заданным лог\пасс
    @Test
    public void authorizeTest() {
        driver.get(PropertyReader.BASEURL);
        HomePage homePage = new HomePage(driver);
        AuthorizationPage authorizationPage = homePage.clickSighnIn();
        MyAccountPage myAccountPage = authorizationPage.doAuthorize("olga_gubkina@ukr.net","123456");
        myAccountPage.checkOnPage(); // Проверяем что мы на странице Аккаунт
        String account = myAccountPage.getAuthorizedAccount();
        Assertions.assertEquals("Olga Gubkina", account); //Equals - для строк\значений\объектов
    }
}

/** Сокращенный вариант
    @Test
    public void authorizeTest() {
        driver.get(PropertyReader.BASEURL);
        HomePage homePage = new HomePage(driver);
        String account = homePage.clickSighnIn()
                .doAuthorize("olga_gubkina@ukr.net","123456")
                .checkOnPage()
                .getAuthorizedAccount();
        Assertions.assertEquals("Olga Gubkina", account);
        }
 */
