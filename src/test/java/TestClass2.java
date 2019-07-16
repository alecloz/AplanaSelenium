import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestClass2 {

    private static WebDriver driver;

    @BeforeClass
    public static void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void stop() {
        driver.close();
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("http://www.sberbank.ru/ru/person");

        clickBtn("//span[contains(text(), 'Страхование')]");
        clickBtn("//a[contains(text(), 'Путешествия и покупки')][contains(@class, 'lg-menu')]");

        String headlineCheck = driver.findElement(By.xpath("//h3[text() = 'Страхование путешественников']")).getText();
        Assert.assertEquals("Assert in headline", "Страхование путешественников", headlineCheck);

        clickBtn("//a[contains(@href, 'vzr')]");

        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));

        clickBtn("//div[text()='Минимальная']");
        clickBtn("//span[contains(text(), 'Оформить')]");

        fillingForm("insured0_surname", "PETROV");
        fillingForm("insured0_name", "PETR");
        fillingForm("insured0_birthDate", "01.01.2001");
        fillingForm("surname", "Иванов");
        fillingForm("name", "Иван");
        fillingForm("middlename", "Иванович");
        clickBtn("//*[@name = 'birthDate']");
        fillingForm("birthDate", "02.02.2000");
        clickBtn("//*[@name = 'male']");
        fillingForm("passport_series", "8264");
        fillingForm("passport_number", "826401");
        fillingForm("issueDate", "02.02.2015");
        fillingForm("issuePlace", "УФМС по Московской области");

        assertCheck("PETROV", "insured0_surname");
        assertCheck("PETR", "insured0_name");
        assertCheck("01.01.2001", "insured0_birthDate");
        assertCheck("Иванов", "surname");
        assertCheck("Иван", "name");
        assertCheck("Иванович", "middlename");
        assertCheck("02.02.2000", "birthDate");
        assertCheck("0", "male");
        assertCheck("8264", "passport_series");
        assertCheck("826401", "passport_number");
        assertCheck("02.02.2015", "issueDate");
        assertCheck("УФМС по Московской области", "issuePlace");

        clickBtn("//span[contains(text(), 'Продолжить')]");

        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Заполнены не все обязательные поля')]"));
        boolean errorCheck = errorMessage.isDisplayed();
        Assert.assertTrue("Error message is not displayed!", errorCheck);
    }

    public void clickBtn(String xpathStr) {
        driver.findElement(By.xpath(xpathStr)).click();
    }

    public void assertCheck(String expected, String name) {
        String template = "//*[@name = '%s']";
        String fullXpath = String.format(template, name);
        String actual = driver.findElement(By.xpath(fullXpath)).getAttribute("value");
        Assert.assertEquals("Assert error", expected, actual);
    }

    public void fillingForm(String name, String textToFill) {
        String template = "//*[@name = '%s']";
        String fullXpath = String.format(template, name);
        driver.findElement(By.xpath(fullXpath)).sendKeys(textToFill);
    }
}
