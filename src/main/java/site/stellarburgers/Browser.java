package site.stellarburgers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Browser {

    private static final Logger logger = LoggerFactory.getLogger(Browser.class);

    public static String getBrowser() {
        // Проверка командного флага
        String browser = System.getProperty("browser");
        if (browser != null && !browser.isEmpty()) {
            return browser;
        }

        // Проверка системной проперти
        browser = System.getProperty("BROWSER");
        if (browser != null && !browser.isEmpty()) {
            return browser;
        }

        // Проверка переменной окружения
        browser = System.getenv("BROWSER");
        if (browser != null && !browser.isEmpty()) {
            return browser;
        }

        // Проверка файла .properties
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            browser = properties.getProperty("browser");
            if (browser != null && !browser.isEmpty()) {
                return browser;
            }
        } catch (IOException e) {
            logger.error("config.properties file not found: " + e.getMessage());
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
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static void closeNotChromeBrowser() {
        String browser = getBrowser();
        if (!browser.equals("Chrome")) {
            closeWebDriver();
        }
    }
}