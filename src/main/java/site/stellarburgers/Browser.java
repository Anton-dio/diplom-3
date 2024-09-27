package site.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Browser {

    public static String getBrowser() {
        // Проверка командного флага
        String browser = System.getProperty("browser");
        if (browser != null) {
            return browser;
        }

        // Проверка системной проперти
        browser = System.getProperty("BROWSER");
        if (browser != null) {
            return browser;
        }

        // Проверка переменной окружения
        browser = System.getenv("BROWSER");
        if (browser != null) {
            return browser;
        }

        // Проверка файла .properties
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            browser = properties.getProperty("browser");
            if (browser != null) {
                return browser;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Значение по умолчанию
        return "Yandex";
    }

    public static void browserChoice() {
        String browser = getBrowser();
        if (browser.equals("Yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            Configuration.browserBinary = "Applications/Yandex.app";
            WebDriver yandexDriver = new ChromeDriver();
            WebDriverRunner.setWebDriver(yandexDriver);
        } else if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            WebDriver chromeDriver = new ChromeDriver();
            WebDriverRunner.setWebDriver(chromeDriver);
        }
    }

    public static void closeNotChromeBrowser() {
        String browser = getBrowser();
        if (!browser.equals("Chrome")) {
            closeWebDriver();
        }
    }

    public static void main(String[] args) {
        browserChoice();
        // Ваш код для тестирования
        closeNotChromeBrowser();
    }
}