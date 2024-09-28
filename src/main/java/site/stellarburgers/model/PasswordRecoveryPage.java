package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage extends Header {

    // локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement signInLink;


    @Step("Переход на страницу логина через ссылку 'Войти'")
    public void clickSignInLink() {
        signInLink.click();
    }
}
