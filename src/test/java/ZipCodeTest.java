import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {

    /*
    Прекондишен: открыть браузер

    1. открыть страницу https://www.sharelane.com/cgi-bin/register.py
    2. ввести в поле Zip code значение 12345
    3. нажать кнопку Continue
    Ожидыемый результат: Мы оказались на странице формы регистрации

    Посткондишен: закрыть браузер

    By.cssSelector("[class=error_message]");
     */

    @Test
    public void checkZipCodeFieldWith5digits() {
        // инициализируем браузер как Chrome
        WebDriver browser = new ChromeDriver();
        // установили неявное ожидание (время ожидание элемента)
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // переходим на страницу
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        // вводим значение 123345
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        // нажимаем кнопку Continue
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        // создаем boolean переменную, которая определяет наличие кнопки Register на странице
        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        // Проверяем, что кнопка действительно есть
        Assert.assertTrue(isDisplayed);

        browser.close();
    }
    @Test
    public void checkZipCodeFieldWith2digits() {
        WebDriver browser = new ChromeDriver();
        // установили неявное время ожидания
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // переходим на страницу
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        // вводим значение 12
        browser.findElement(By.name("zip_code")).sendKeys("12");
        // нажимаем кнопку Continue
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        // создаем boolean переменную, которая определяет появление ошибки
        boolean isErrorDisplayed = browser.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        // Проверяем, что ошибка отображается
        Assert.assertTrue(isErrorDisplayed);

        browser.close();
    }
    @Test
    public void checkZipCodeWithMoreThen5digits() { //этот тест зафейлится, потому что баг
        WebDriver browser = new ChromeDriver();
        try {
            // установили неявное время ожидания
            browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // переходим на страницу
            browser.get("https://www.sharelane.com/cgi-bin/register.py");
            // вводим значение 123456
            browser.findElement(By.name("zip_code")).sendKeys("123456");
            // нажимаем кнопку Continue
            browser.findElement(By.cssSelector("[value=Continue]")).click();

            // создаем boolean переменную, которая определяет появление ошибки
            boolean isErrorDisplayed = browser.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
            // Проверяем, что ошибка отображается
            Assert.assertTrue(isErrorDisplayed);
        } finally {
            browser.quit();
        }
    }
}
