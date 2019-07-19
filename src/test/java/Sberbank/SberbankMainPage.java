package Sberbank;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberbankMainPage extends BasePageSberbank{

    @FindBy(xpath = "//span[contains(text(), 'Страхование')]")
    public WebElement insuranceButton;

    @FindBy(xpath = "//a[contains(text(), 'Путешествия и покупки')][contains(@class, 'lg-menu')]")
    public WebElement travelAndPurchasesBtn;

    @Step("Insurance button click")
    public void insuranceBtn(){
        waitForReady(insuranceButton).click();
    }

    @Step("Open Sberbank purchases and travel page")
    public SberbankTravelInsurancePage openTravelInsurance(){
        waitForReady(travelAndPurchasesBtn).click();
        return new SberbankTravelInsurancePage();
    }
}
