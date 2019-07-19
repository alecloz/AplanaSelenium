package Sberbank;

import org.junit.*;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestObjectPageSber {

    @Test
    public void test() {

        SberbankMainPage mainPage = new SberbankMainPage();
        mainPage.insuranceBtn();
        SberbankTravelInsurancePage insuranceAndTravelPage = mainPage.openTravelInsurance();
        insuranceAndTravelPage.textDisplayedCheck(insuranceAndTravelPage.headlineInsuranceAndTravel);
        SberbankInsurancePage insurancePage = insuranceAndTravelPage.makeRequest();

        ArrayList<String> tabs = new ArrayList<>(insurancePage.driver.getWindowHandles());
        insurancePage.driver.switchTo().window(tabs.get(0));
        insurancePage.driver.close();
        insurancePage.driver.switchTo().window(tabs.get(1));

        insurancePage.findAndClickMinimumBtn();
        insurancePage.findAndClickRegistrationBtn();
        insurancePage.fillingForm(insurancePage.insuredSurname, "PETROV");
        insurancePage.fillingForm(insurancePage.insuredName, "PETR");
        insurancePage.fillingForm(insurancePage.insuredBirthDate, "01.01.2000");
        insurancePage.buttonClick(insurancePage.insuredBirthDateTable);
        insurancePage.fillingForm(insurancePage.surname, "Иванов");
        insurancePage.fillingForm(insurancePage.name, "Иван");
        insurancePage.fillingForm(insurancePage.middlename, "Иванович");
        JavascriptExecutor jse = (JavascriptExecutor)insurancePage.driver;
        jse.executeScript("scroll(0, 250);");
        insurancePage.buttonClick(insurancePage.birthDateTable);
        insurancePage.fillingForm(insurancePage.birthDate, "21.03.1999");
        insurancePage.buttonClick(insurancePage.birthDateTable);
        insurancePage.checkBox(insurancePage.female);
        insurancePage.fillingForm(insurancePage.passportSeries, "8264");
        insurancePage.fillingForm(insurancePage.passportNumber, "826401");
        insurancePage.buttonClick(insurancePage.issueDateTable);
        insurancePage.fillingForm(insurancePage.issueDate, "02.02.2015");
        insurancePage.buttonClick(insurancePage.issueDateTable);
        jse.executeScript("scroll(0, 750);");
        insurancePage.fillingForm(insurancePage.issuePlace, "УФМС по Московской области");
        insurancePage.checkAssert(insurancePage.insuredSurname, "PETROV");
        insurancePage.checkAssert(insurancePage.insuredName, "PETR");
        insurancePage.checkAssert(insurancePage.insuredBirthDate, "01.01.2000");
        insurancePage.checkAssert(insurancePage.surname, "Иванов");
        insurancePage.checkAssert(insurancePage.name, "Иван");
        insurancePage.checkAssert(insurancePage.middlename, "Иванович");
        insurancePage.checkAssert(insurancePage.birthDate, "21.03.1999");
        insurancePage.assertCheckBox(insurancePage.female);
        insurancePage.checkAssert(insurancePage.passportSeries, "8264");
        insurancePage.checkAssert(insurancePage.passportNumber, "826401");
        insurancePage.checkAssert(insurancePage.issueDate, "02.02.2015");
        insurancePage.checkAssert(insurancePage.issuePlace, "УФМС по Московской области");
        insurancePage.buttonClick(insurancePage.continueButton);
        insurancePage.textDisplayedCheck(insurancePage.errorMessage);
    }
    @Before
    public void start(){
        InitSberbank.startBrowser("http://www.sberbank.ru/ru/person");
        InitSberbank.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        InitSberbank.getDriver().manage().window().maximize();
    }
    @After
    public void stop(){
        InitSberbank.getDriver().close();
        InitSberbank.getDriver().quit();
    }
}
