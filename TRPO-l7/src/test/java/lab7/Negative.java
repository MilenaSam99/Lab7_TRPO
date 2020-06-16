package lab7;
import Data.Account;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class Negative {

    @DataProvider(name = "UserData")
    public Object[] UserData() {
        Account[] res = new Account[1];
        res[0] = new Account("Anna", "",Math.random() + "@mail.ru", "123456789",
                1, "June", 1999, "st. Lenina", "Volgograd",
                21, "New York", 40006, "8 800 555 35-35"
        );
        return res;
    }

    WebDriver driver = new FirefoxDriver();
    //открываем браузер
    @BeforeClass()
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\EdaDil\\Desktop\\TRPO7-master\\geckodriver.exe");
        driver = new FirefoxDriver();
        //open the url
        driver.get("http://automationpractice.com/");

    }
    //закрываем браузер
    @AfterClass()
    public void downUp(){
        driver.quit();
    }

    @Severity(value = SeverityLevel.NORMAL)
    @DisplayName("Регистрация без фамилии")
    @Description("Негативный тест регистрации")
    @Test(dataProvider = "UserData")
    public void mainTest(Account user) {
        WebElement signInLink, input;
        List errors;
        // Переходим на страницу регистрации
        signInLink = (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.className("login")));
        signInLink.click();

        // Вводим email
        (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.id("email_create"))).sendKeys(user.email);
        // Жмём "create an account"
        (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.id("create-account_form"))).submit();

        input = (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.id("customer_firstname")));
        input.sendKeys(user.firstName);
        input = driver.findElement(By.id("customer_lastname"));
        input.sendKeys(user.lastName);
        input = driver.findElement(By.id("passwd"));
        input.sendKeys(user.password);
        input = driver.findElement(By.id("days"));
        input.sendKeys(Integer.toString(user.day));
        input = driver.findElement(By.id("months"));
        input.sendKeys(user.month);
        input = driver.findElement(By.id("years"));
        input.sendKeys(Integer.toString(user.year));

        input = driver.findElement(By.id("firstname"));
        input.sendKeys(user.firstName);
        input = driver.findElement(By.id("lastname"));
        input.sendKeys(user.lastName);
        input = driver.findElement(By.id("address1"));
        input.sendKeys(user.address);
        input = driver.findElement(By.id("city"));
        input.sendKeys(user.city);
        input = driver.findElement(By.id("id_country"));
        input.sendKeys(Integer.toString(user.country));
        input = (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.id("id_state")));
        input.sendKeys(user.state);
        input = driver.findElement(By.id("postcode"));
        input.sendKeys(Integer.toString(user.postalCode));
        input = driver.findElement(By.id("phone_mobile"));
        input.sendKeys(user.phone);

        // Регистрируемся
        driver.findElement(By.id("submitAccount")).click();
        // Ждём, пока не появятся ошибки
        (new WebDriverWait(driver, 5)).until(presenceOfElementLocated(By.cssSelector(".alert.alert-danger")));

        errors = driver.findElements(By.cssSelector(".alert.alert-danger li b"));

        // Отсутствующее поле
        String[] missingFields = {"lastname"};
        for(Object err : errors) {
            assertThat(missingFields, hasItemInArray(((WebElement) err).getText()));
        }
    }
}
