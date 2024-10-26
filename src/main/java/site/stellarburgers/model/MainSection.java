package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainSection extends HeaderSection {

    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement checkoutButton;

    @Step("Переход на страницу логина через кнопку 'Войти в аккаунт'")
    public void clickSignInButton() {
        signInButton.click();
    }

    public boolean checkIsCheckOutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public boolean checkIsSignInButtonEnabled() {
        return signInButton.isEnabled();
    }
}