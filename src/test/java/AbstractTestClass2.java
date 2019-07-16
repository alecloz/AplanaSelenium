import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AbstractTestClass2 {

    WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void stop() {
        driver.close();
        driver.quit();
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
