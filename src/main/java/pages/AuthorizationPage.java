package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthorizationPage  {
    private static String pageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private   WebDriver driver;// = new ChromeDriver();
    public By signInLocator = By.xpath("//button[@id='SubmitLogin']");
    public By emailInputLocator = By.xpath("//input[@id='email']");
    //public By emailInputLocator = By.cssSelector("input#email"); // элемент #=id = email
    public By passwordInputLocator = By.xpath("//input[@id='passwd']");
    public By navigationPanelLocator = By.cssSelector("span.navigation_page");

    public AuthorizationPage(WebDriver driver) {this.driver = driver;}

    public AuthorizationPage open() {
        driver.navigate().to(pageURL);
        return this;
    }

    public MyAccountPage doAuthorize(String login, String password) { //Метод возвращает страницу Аккаунт
        //driver.findElement(emailInputLocator);
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys(login);

        WebElement passInput = driver.findElement(passwordInputLocator);
        passInput.sendKeys(password);

        WebElement signInButton = driver.findElement(signInLocator);
        signInButton.click();

        return new MyAccountPage(driver);
    }

    public AuthorizationPage checkOnPage() {
        WebElement navigationPanel = driver.findElement(navigationPanelLocator);
        Assertions.assertTrue(navigationPanel.isDisplayed());
        Assertions.assertEquals("Authentication", navigationPanel.getText());
        return this;
    }
}
