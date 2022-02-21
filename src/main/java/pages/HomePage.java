package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver; // private+final = constanta
    private final By signInLocator = By.className("login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public AuthorizationPage clickSighnIn() {
        WebElement signInButton = driver.findElement(signInLocator);
        signInButton.click();
        return new AuthorizationPage(driver); // Результат работы - новая страница с кот будем работать
    }

    public HomePage checkOnPage() {
        Assertions.assertEquals("My store", driver.getTitle(), "Not a home page" +
                "current page is: " + driver.getCurrentUrl());
        return this;
    }

    public void waitOnPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.pollingEvery(Duration.ofSeconds(2)); // Каждые 2 сек пробуем отобразился ли элемент
        wait.until(ExpectedConditions.presenceOfElementLocated(signInLocator)); // ожидаем что элемент есть на стр
    }

}
