package Sberbank;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberbankTravelInsurancePage extends BasePageSberbank{

    @FindBy(xpath = "//h3[text() = 'Страхование путешественников']")
    public WebElement headlineInsuranceAndTravel;

    @FindBy(xpath = "//a[contains(@href, 'vzr')]")
    public WebElement makeRequestBtn;

    @Step("Check displayed headline text")
    public void textDisplayedCheck(WebElement element){
        waitForReady(element);
        boolean b = element.isDisplayed();
        Assert.assertTrue("The text is not displayed", b);
    }

    @Step("Open Sberbank insurance page")
    public SberbankInsurancePage makeRequest(){
        waitForReady(makeRequestBtn).click();
        return new SberbankInsurancePage();
    }
}
