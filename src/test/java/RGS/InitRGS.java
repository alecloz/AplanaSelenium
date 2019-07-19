package RGS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitRGS {

    public static WebDriver driver;

    public static void startBrowser(String url) {
        driver = new ChromeDriver();
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
