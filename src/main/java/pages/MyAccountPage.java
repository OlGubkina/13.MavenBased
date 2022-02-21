package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyAccountPage {
    private WebDriver driver;
    public By accountLocator = By.cssSelector("a.account span");// a-родитель; (.)-класс; span-искомый элемент
    public By navigationPanelLocator = By.cssSelector("span.navigation_page");

    // *** xPath
    // public By navigationSpanLocator = By.xpath("//span[@class='navigation_page']");
    //WebElement navigationSpan = driver.findElement(By.xpath("//span[@class='navigation_page']"));

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public MyAccountPage checkOnPage() {
        assertTrue(driver.findElement(navigationPanelLocator).isDisplayed());

    // *** xPath:
    // assertTrue(driver.findElement(navigationSpanLocator).isDisplayed()); //assertEquals(navigationSpan.getText(),"My account");
        return this;
    }

    public String getAuthorizedAccount() {
        return driver.findElement(accountLocator).getText(); // получаем текс,о находящийся внутри элемента
    }
}
