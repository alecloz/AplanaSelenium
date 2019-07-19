package RGS;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RGSMainPage extends BasePageRGS {

    @FindBy(xpath = "//li[contains(@class, 'dropdown')]/a[contains(text(), 'Страхование')]")
    public WebElement dropDownButton;

    @FindBy(xpath = "//ul[contains(@class, 'collapse')]//a[contains(text(), 'ДМС')]")
    public WebElement openDMSButton;

    @Step("Press dropDown button")
    public void dropDownBtn(){
        waitForReady(dropDownButton).click();
    }
    @Step("Open DMS web-page RGS")
    public RGSPageDMS openDMS(){
        waitForReady(openDMSButton).click();
        return new RGSPageDMS();
    }
}
