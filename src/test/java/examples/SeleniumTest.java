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
        driver.get(PropertyReader.BASEURL); //=driver.get("http://automationpractice.com/");
        HomePage homePage = new HomePage(driver);
        homePage.checkOnPage(); // на главной странице title = My Store
        WebElement contactButton = driver.findElement(By.xpath("//a[@title='Contact us']"));
        if (contactButton.isDisplayed())
            contactButton.click();
        assertTrue(driver.getCurrentUrl().contains("contact"));
    }

    /** ДЗ_lesson_15. Регистрация нового пользователя */
    @Test
    public void NewUserRegistration () {

    }


/** Черновик
    public void NewUserRegistration () throws InterruptedException {
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

        System.out.printf("Registration of the new user. %n email: %s%n Password: %s%n", email, pass);

        driver.get(PropertyReader.BASEURL); //driver.get("http://automationpractice.com/");
        TimeUnit TimeUnit = java.util.concurrent.TimeUnit.SECONDS;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit); //Ждём элемент 10 сек если не загрузился

        HomePage homePage = new HomePage(driver);
        homePage.checkOnPage(); // Проверяем что мы на главной странице (title = My Store)

        homePage.clickSignIn(); //Click по signIn кнопке. Переход на стр регистрации
        //Вводим почту
        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        //WebElement sButton =
                driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        //sButton.click();

        assertTrue(driver.getCurrentUrl().contains("authentication"));

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
        //driver.findElement(By.xpath("//button[@id='submitAccount']")).click();
    }
*/








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
        AuthorizationPage authorizationPage = homePage.clickSignIn();
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
