import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestClass {
    WebDriver driver;

    @Test
    public void test() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rgs.ru");

        WebElement dropdownBtn = driver.findElement(By.xpath("//li[contains(@class, 'dropdown')]/a[contains(text(), 'Страхование')]"));
        dropdownBtn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement dmsBtn = driver.findElement(By.xpath("//ul[contains(@class, 'collapse')]//a[contains(text(), 'ДМС')]"));
        dmsBtn.click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 1,
                1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(), 'ДМС')][contains(text(), '— добровольное медицинское страхование')]"))));
        WebElement titleHere = driver.findElement(By.xpath("//h1[contains(text(), 'ДМС')][contains(text(), '— добровольное медицинское страхование')]"));
        boolean b1 = titleHere.isDisplayed();
        Assert.assertTrue(b1);

        WebElement sendMessageBtn = driver.findElement(By.xpath("//a[contains(text(), 'Отправить заявку')]"));
        sendMessageBtn.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//b[contains(text(), 'Заявка на добровольное медицинское страхование')]"))));
        WebElement bodyText = driver.findElement(By.xpath("//b[contains(text(), 'Заявка на добровольное медицинское страхование')]"));
        boolean b2 = bodyText.isDisplayed();
        Assert.assertTrue(b2);

        contactInformationFilling("Фамилия", "Иванов");
        contactInformationFilling("Имя", "Иван");
        contactInformationFilling("Отчество", "Иванович");

        WebElement dropdownList = driver.findElement(By.xpath("//select[@name = 'Region']"));
        Select select = new Select(dropdownList);
        select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        select.selectByIndex(1);

        contactInformationFilling("Телефон", "9997771234");
        contactInformationFilling("Эл. почта", "qwertyqwerty");

        WebElement newComment = driver.findElement(By.xpath("//textarea[contains(@data-bind, 'value: Comment,')]"));
        newComment.sendKeys("Ввод комментария!");

        WebElement checkBox = driver.findElement(By.xpath("//label[contains(text(), 'Я согласен')]"));
        checkBox.click();

        String lastName = driver.findElement(By.xpath("//*[text() = 'Фамилия']/following::input[1]")).getAttribute("value");
        Assert.assertEquals("Иванов", lastName);
        String firstName = driver.findElement(By.xpath("//*[text() = 'Имя']/following::input[1]")).getAttribute("value");
        Assert.assertEquals("Иван", firstName);
        String fathersName = driver.findElement(By.xpath("//*[text() = 'Отчество']/following::input[1]")).getAttribute("value");
        Assert.assertEquals("Иванович", fathersName);
        String regionValue = driver.findElement(By.xpath("//select[@name = 'Region']")).getAttribute("value");
        Assert.assertEquals("77", regionValue);
        String phoneNumber = driver.findElement(By.xpath("//*[text() = 'Телефон']/following::input[1]")).getAttribute("value");
        Assert.assertEquals("+7 (999) 777-12-34", phoneNumber);
        String emailValue = driver.findElement(By.xpath("//*[text() = 'Эл. почта']/following::input[1]")).getAttribute("value");
        Assert.assertEquals("qwertyqwerty", emailValue);
        String comment = driver.findElement(By.xpath("//textarea[contains(@data-bind, 'value: Comment,')]")).getAttribute("value");
        Assert.assertEquals("Ввод комментария!", comment);

        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='button-m'][contains(text(), 'Отправить')]"));
        sendBtn.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class = 'validation-error-text']"))));
        WebElement wrongEmailMessage = driver.findElement(By.xpath("//*[@class = 'validation-error-text']"));
        boolean b3 = wrongEmailMessage.isDisplayed();
        Assert.assertTrue(b3);

        WebElement wrongEmail = driver.findElement(By.xpath("//*[@name='Email']"));
        wrongEmail.clear();
        wrongEmail.sendKeys("qwerty@yandex.ru");

        driver.close();
        driver.quit();
    }
    public void contactInformationFilling(String name, String textToFill) {
        String template = "//*[text() = '%s']/following::input[1]";
        String fullXpath = String.format(template, name);
        driver.findElement(By.xpath(fullXpath)).sendKeys(textToFill);
    }
}
