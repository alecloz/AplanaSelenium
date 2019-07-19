package Sberbank;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberbankInsurancePage extends BasePageSberbank {

    @FindBy(xpath = "//div[text()='Минимальная']")
    public WebElement minimumButton;

    @FindBy(xpath = "//span[contains(text(), 'Оформить')]")
    public WebElement registrationButton;

    @FindBy(xpath = "//*[@name = 'insured0_surname']")
    public WebElement insuredSurname;

    @FindBy(xpath = "//*[@name = 'insured0_name']")
    public WebElement insuredName;

    @FindBy(xpath = "//*[@name = 'insured0_birthDate']")
    public WebElement insuredBirthDate;

    @FindBy(xpath = "//*[@name = 'insured0_birthDate']/following-sibling::*[1]")
    public WebElement insuredBirthDateTable;

    @FindBy(xpath = "//*[@name = 'surname']")
    public WebElement surname;

    @FindBy(xpath = "//*[@name = 'name']")
    public WebElement name;

    @FindBy(xpath = "//*[@name = 'middlename']")
    public WebElement middlename;

    @FindBy(xpath = "//input[@name = 'birthDate']/following-sibling::*[1]")
    public WebElement birthDateTable;

    @FindBy(xpath = "//input[@name = 'birthDate']")
    public WebElement birthDate;

    @FindBy(xpath = "//*[@name = 'female']")
    public WebElement female;

    @FindBy(xpath = "//*[@name = 'passport_series']")
    public WebElement passportSeries;

    @FindBy(xpath = "//*[@name = 'passport_number']")
    public WebElement passportNumber;

    @FindBy(xpath = "//input[@name = 'issueDate']/following-sibling::*[1]")
    public WebElement issueDateTable;

    @FindBy(xpath = "//input[@name = 'issueDate']")
    public WebElement issueDate;

    @FindBy(xpath = "//*[@name = 'issuePlace']")
    public WebElement issuePlace;

    @FindBy(xpath = "//span[contains(text(), 'Продолжить')]")
    public WebElement continueButton;

    @FindBy(xpath = "//div[contains(text(), 'Заполнены не все обязательные поля')]")
    public WebElement errorMessage;

    @Step("Find and choose minimal tariff")
    public void findAndClickMinimumBtn() {
        WebElement element = minimumButton;
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", element);
        this.buttonClick(minimumButton);
    }

    @Step("Open registration form")
    public void findAndClickRegistrationBtn() {
        WebElement element = registrationButton;
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", element);
        this.buttonClick(registrationButton);
    }

    @Step("Check displayed text")
    public void textDisplayedCheck(WebElement element) {
        waitForReady(element);
        boolean b = element.isDisplayed();
        Assert.assertTrue("The text is not displayed", b);
        if (element.equals(errorMessage)){
            takeScreenshot();
        }
    }

    @Step("Click some button")
    public void buttonClick(WebElement element) {
        waitForReady(element);
        element.click();
    }

    @Step("Filling some form")
    public void fillingForm(WebElement element, String textToFill) {
        waitForReady(element);
        element.sendKeys(textToFill);
    }

    @Step("Check asserts")
    public void checkAssert(WebElement element, String expectedText) {
        waitForReady(element);
        String actualText = element.getAttribute("value");
        Assert.assertEquals("Assert error!", expectedText, actualText);
    }

    @Step("Check assert checkbox")
    public void assertCheckBox(WebElement element) {
        boolean b = element.isSelected();
        Assert.assertTrue("CheckBox error!", b);
    }

    @Step("Checkbox selected")
    public void checkBox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }
}
