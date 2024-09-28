package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage extends Header {

    // локатор поля ввода имени
    @FindBy(how = How.XPATH, using = "//div[label[text()='Имя']]/input")
    private SelenideElement nameInputField;

    // локатор поля ввода email
    @FindBy(how = How.XPATH, using = "//div[label[text()='Email']]/input")
    private SelenideElement emailInputField;

    // локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInputField;

    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    // локатор текста ошибки "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordText;

    // локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    private SelenideElement signInLink;


    public void setName(String name) {
        nameInputField.setValue(name);
    }

    public void setEmail(String email) {
        emailInputField.setValue(email);
    }

    public void setPassword(String password) {
        passwordInputField.setValue(password);
    }

    public void clickRegisterButton() {
        registerButton.click();
    }
    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    public boolean checkIsIncorrectPasswordTextVisible() {
        return incorrectPasswordText.isDisplayed();
    }

    @Step("Переход на страницу логина через ссылку 'Войти'")
    public void clickSignInLink() {
        signInLink.click();
    }
}
