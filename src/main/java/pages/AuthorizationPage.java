package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuthorizationPage  {
    static String pageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    static WebDriver driver;// = new ChromeDriver();
    By signInLocator = By.xpath("//button[@id='SubmitLogin']");
    By emailInputLocator = By.xpath("//input[@id='email']");
    By passwordInputLocator = By.xpath("//input[@id='passwd']");

    public static AuthorizationPage navigeteHere(WebDriver driverIn) {
        driver = driverIn;
        driver.navigate().to(pageURL);
        return new AuthorizationPage();
    }

    public void doAuthorize() {
        //driver.findElement(emailInputLocator);
        WebElement emailInput = driver.findElement(emailInputLocator);
        emailInput.sendKeys("olga_gubkina@ukr.net");

        WebElement passInput = driver.findElement(passwordInputLocator);
        passInput.sendKeys("123456");

        WebElement signInButton = driver.findElement(signInLocator);
        signInButton.click();



    }
}
