package site.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.InputStream;
import java.util.Properties;

public class Browser {

    private static Properties properties;
    private static WebDriver driver;

    static {
        properties = new Properties();
        try (InputStream input = Browser.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            System.err.println("config.properties file not found: " + e.getMessage());
        }
    }

    public static WebDriver browserChoice() {
        String browser = properties.getProperty("browser", "chrome");
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static void closeNotChromeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}