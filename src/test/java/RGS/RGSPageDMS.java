package RGS;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

public class RGSPageDMS extends BasePageRGS {

    @FindBy(xpath = "//h1[contains(text(), 'ДМС')][contains(text(), '— добровольное медицинское страхование')]")
    public WebElement headlineTextDMS;
    @FindBy(xpath = "//a[contains(text(), 'Отправить заявку')]")
    public WebElement requestBtn;
    @FindBy(xpath = "//b[contains(text(), 'Заявка на добровольное медицинское страхование')]")
    public WebElement headlineTextRequest;
    @FindBy(xpath = "//*[text() = 'Фамилия']/following::input[1]")
    public WebElement lastName;
    @FindBy(xpath = "//*[text() = 'Имя']/following::input[1]")
    public WebElement name;
    @FindBy(xpath = "//*[text() = 'Отчество']/following::input[1]")
    public WebElement patronymic;
    @FindBy(xpath = "//select[@name = 'Region']")
    public WebElement region;
    @FindBy(xpath = "//*[text() = 'Телефон']/following::input[1]")
    public WebElement phoneNumber;
    @FindBy(xpath = "//*[text() = 'Эл. почта']/following::input[1]")
    public WebElement email;
    @FindBy(xpath = "//textarea[contains(@data-bind, 'value: Comment,')]")
    public WebElement comment;
    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement checkBoxAccept;
    @FindBy(xpath = "//button[@id='button-m'][contains(text(), 'Отправить')]")
    public WebElement sendButton;
    @FindBy(xpath = "//*[@class = 'validation-error-text']")
    public WebElement errorMessageIsDisplayed;

    @Step("Check displayed text")
    public void textDisplayedCheck(WebElement element){
        waitForReady(element);
        boolean b = element.isDisplayed();
        Assert.assertTrue("The text is not displayed", b);
    }

    @Step("Some button click")
    public void buttonClick(WebElement element){
        waitForReady(element);
        element.click();
    }

    @Step("Filling some form")
    public void fillingForm(WebElement element, String textToFill){
        waitForReady(element);
        element.sendKeys(textToFill);
        if (textToFill.equals("qwerty@yandex.ru")){
            takeScreenshot();
        }
    }

    @Step("Filling region form")
    public void fillingFormRegion(WebElement element){
        waitForReady(element);
        Select select = new Select(element);
        select.selectByVisibleText(Arrays.asList(element.getText().split("\n")).get(1));
    }

    @Step("Check asserts")
    public void checkAssert (WebElement element, String expectedText){
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
    public void checkBoxFilling(WebElement element) {
            element.click();
    }
}
