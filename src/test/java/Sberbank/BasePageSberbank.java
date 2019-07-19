package Sberbank;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageSberbank {
    WebDriver driver;
    WebDriverWait wait;

    public void waitForPageLoad(){
        waitForReady(By.xpath("//body"));
    }

    public BasePageSberbank(){
        this.driver = InitSberbank.getDriver();
        this.wait = new WebDriverWait(driver, 60);
        new WebDriverWait(driver, 30 , 200);
        PageFactory.initElements(driver, this);
        waitForPageLoad();
    }

    public WebElement waitForReady(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public WebElement waitForReady(By by){
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
