import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {

    /*
    Прекондишен: открыть браузер

    1. открыть страницу https://www.sharelane.com/cgi-bin/register.py
    2. ввести в поле Zip code значение 12345
    3. нажать кнопку Continue
    4. откроется страница регистрации (https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345)
    5. ввести в поле First name: Test
    6. ввести в поле Email: test@gmail.com
    7. ввести в поле Password: test123
    8. ввести в поле Confirm Password: test123
    9. нажать кнопку Register
    Ожидыемый результат: Мы оказались на странице с подтвержденим, что аккаунт создан

    Посткондишен: закрыть браузер
     */

    @Test
    public void checkRegistrationWithRequiredField() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        browser.findElement(By.name("first_name")).sendKeys("Test");
        browser.findElement(By.name("email")).sendKeys("test@gmail.com");
        browser.findElement(By.name("password1")).sendKeys("test123");
        browser.findElement(By.name("password2")).sendKeys("test123");
        browser.findElement(By.cssSelector("[value=Register]")).click();

        // создаем boolean переменную, которая определяет появление ошибки
        boolean isCreatedAccount = browser.findElement(By.cssSelector("[class=confirmation_message]")).isDisplayed();
        // Проверяем, что Account is created сообщение отображается
        Assert.assertTrue(isCreatedAccount);

        browser.close();
    }
    @Test
    public void checkRegistrationWithInvalidEmail() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        browser.findElement(By.name("first_name")).sendKeys("Test");
        browser.findElement(By.name("email")).sendKeys("test.com");
        browser.findElement(By.name("password1")).sendKeys("test123");
        browser.findElement(By.name("password2")).sendKeys("test123");
        browser.findElement(By.cssSelector("[value=Register]")).click();

        // создаем boolean переменную, которая определяет -> появилась ошибка или нет
        boolean isError = browser.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        // Проверяем, что сообщение об ошибке отображается
        Assert.assertTrue(isError);

        browser.close();
    }
    @Test
    public void checkRegistrationWithoutPasswordField() {
        WebDriver browser = new ChromeDriver();
        // установили неявное время ожидания
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // переходим на страницу
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        // вводим значение 12345
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        // нажимаем кнопку Continue
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        browser.findElement(By.name("first_name")).sendKeys("Test");
        browser.findElement(By.name("email")).sendKeys("test.com");
        browser.findElement(By.name("password2")).sendKeys("test123");
        browser.findElement(By.cssSelector("[value=Register]")).click();

        // создаем boolean переменную, которая появление ошибки
        boolean isError = browser.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        // Проверяем, что сообщение об ошибке отображается
        Assert.assertTrue(isError);

        browser.close();
    }
}
