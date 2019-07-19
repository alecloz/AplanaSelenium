package RGS;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestObjectPageRGS {

    @Test
    public void test() {

        RGSMainPage mainPage = new RGSMainPage();
        mainPage.dropDownBtn();
        RGSPageDMS pageDMS = mainPage.openDMS();
        pageDMS.textDisplayedCheck(pageDMS.headlineTextDMS);
        pageDMS.buttonClick(pageDMS.requestBtn);
        pageDMS.textDisplayedCheck(pageDMS.headlineTextRequest);
        pageDMS.fillingForm(pageDMS.lastName, "Иванов");
        pageDMS.fillingForm(pageDMS.name, "Иван");
        pageDMS.fillingForm(pageDMS.patronymic, "Иванович");
        pageDMS.fillingFormRegion(pageDMS.region);
        pageDMS.fillingForm(pageDMS.phoneNumber, "9997771234");
        pageDMS.fillingForm(pageDMS.email, "qwertyqwerty");
        pageDMS.fillingForm(pageDMS.comment, "Текст комментария");
        pageDMS.checkBoxFilling(pageDMS.checkBoxAccept);
        pageDMS.checkAssert(pageDMS.lastName, "Иванов");
        pageDMS.checkAssert(pageDMS.name, "Иван");
        pageDMS.checkAssert(pageDMS.patronymic, "Иванович");
        pageDMS.checkAssert(pageDMS.region, "77");
        pageDMS.checkAssert(pageDMS.phoneNumber, "+7 (999) 777-12-34");
        pageDMS.checkAssert(pageDMS.email, "qwertyqwerty");
        pageDMS.checkAssert(pageDMS.comment, "Текст комментария");
        pageDMS.assertCheckBox(pageDMS.checkBoxAccept);
        pageDMS.buttonClick(pageDMS.sendButton);
        pageDMS.textDisplayedCheck(pageDMS.errorMessageIsDisplayed);
        pageDMS.email.clear();
        pageDMS.fillingForm(pageDMS.email, "qwerty@yandex.ru");
    }
    @Before
    public void start(){
        InitRGS.startBrowser("https://rgs.ru");
        InitRGS.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        InitRGS.getDriver().manage().window().maximize();
    }
    @After
    public void stop(){
        InitRGS.getDriver().close();
        InitRGS.getDriver().quit();
    }
}
