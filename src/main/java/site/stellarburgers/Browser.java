package site.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Browser {

    // Выбор браузера
    public final static String BROWSER = "Yandex"; // "Yandex" or "Chrome"

    public static void browserChoice() {
        if (BROWSER.equals("Yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            Configuration.browserBinary = "Applications/Yandex.app";
            WebDriver yandexDriver = new ChromeDriver();
            WebDriverRunner.setWebDriver(yandexDriver);
        }
    }

    public static void closeNotChromeBrowser() {
        if (!BROWSER.equals("Chrome")) closeWebDriver();
    }
}
