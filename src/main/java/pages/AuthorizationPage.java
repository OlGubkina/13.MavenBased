package pages;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizationPage  {
    private static String pageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    private WebDriver driver;// = new ChromeDriver();
    public AuthorizationPage(WebDriver driver) {this.driver = driver;}

    public By signInLocator = By.xpath("//button[@id='SubmitLogin']");
    public By emailInputLocator = By.xpath("//input[@id='email']");
    //public By emailInputLocator = By.cssSelector("input#email"); // элемент #=id = email
    public By passwordInputLocator = By.xpath("//input[@id='passwd']");
    public By navigationPanelLocator = By.cssSelector("span.navigation_page");

    //Random DATA
    //Fairy + Person для генерации тестовых данных (библиотека в pom)
    Fairy fairy = Fairy.create();
    Person person = fairy.person();
    String email = person.getEmail();
    String fName = person.getFirstName();
    String lName = person.getLastName();
    String pass = person.getPassword();
    String sex = String.valueOf(person.getSex());
    //DateOfB to int
    int year = Integer.parseInt(String.valueOf(person.getDateOfBirth()).substring(0,4));
    int month = Integer.parseInt(String.valueOf(person.getDateOfBirth()).substring(5,7));
    int day = Integer.parseInt(String.valueOf(person.getDateOfBirth()).substring(8,10));

    public AuthorizationPage open() {
        driver.navigate().to(pageURL);
        return this;
    }

    public MyAccountPage doAuthorize(String login, String password) {
        //Метод возвращает страницу Аккаунт
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
        System.out.println("checkOnPage <<Authorization page>> - OK");
        return this;
    }

    public void createAnAccountButtonClick() {
        //Вводим почту и переходим дальше
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        //Assertions.assertTrue(driver.getCurrentUrl().contains("*.account-creation"));
        //http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation
        System.out.println("<<Create new user page>> - OK");
        }

    public void fillTheForm() {
        System.out.printf("Registration of the new user: %n First name: %s%n Last name: %s%n email: %s%n Password: %s%n", fName, lName, email, pass);
        //YOUR PERSONAL INFORMATION
        //radiobutton -OK
        if (sex == "MALE") driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
        if (sex == "FEMALE") driver.findElement(By.xpath("//input[@id='id_gender2']")).click();
        //First name, Last name -OK
        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(fName);
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(lName);
        //Password -OK
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(pass);
        //Date of Birth - dropdown -OK
        day++;
        month++;
        year = 2022-year+2;
        //Использовать value dropdownlist????
        String xPathForDay = "//select[@id='days']/option[" + day + "]";
        String xPathForMonth = "//select[@id='months']/option[" + month + "]";
        String xPathForYear = "//select[@id='years']/option[" + year + "]";
        driver.findElement(By.xpath(xPathForDay)).click();
        driver.findElement(By.xpath(xPathForMonth)).click();
        driver.findElement(By.xpath(xPathForYear)).click();
        //Checkbox - OK
        driver.findElement(By.xpath("//input[@id='newsletter']")).click();

        //YOUR ADDRESS section
        //driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fName);
        //driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lName);
        String addr1 = String.valueOf(person.getAddress().getAddressLine1());
        String addr2 = String.valueOf(person.getAddress().getAddressLine2());
        String addr3 = addr1+" "+addr2;
        driver.findElement(By.xpath("//input[@id='address1']")).sendKeys(addr3);
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys(person.getAddress().getCity());
        driver.findElement(By.xpath("//div[@id='uniform-id_state'] //option[3]")).click(); //ШТАТ
        driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(person.getAddress().getPostalCode());
        driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(person.getTelephoneNumber());
        driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys(person.getTelephoneNumber());
        driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
        }

    public void checkTheRegIsOk() {
        String newUserName = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")).getText();
        System.out.println("Created acc for: " + newUserName);
    }
}
