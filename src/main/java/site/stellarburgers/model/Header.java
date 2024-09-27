package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Header {

    // локатор ссылки "Конструктор"
    @FindBy(how = How.XPATH, using = "//a[@class='AppHeader_header__link__3D_hX' and @href='/']")
    protected SelenideElement constructorLink;

    // локатор ссылки-логотипа
    @FindBy(how = How.CSS, using = "div.AppHeader_header__logo__2D0X2")
    protected SelenideElement logoLinc;

    // локатор ссылки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//a[@href='/account']")
    protected SelenideElement personalAccountLinc;

    @Step("Переход на страницу конструктора")
    public void clickConstructorLink() {
        constructorLink.click();
    }

    @Step("Переход на главную страницу")
    public void clickLogoLink() {
        logoLinc.click();
    }

    @Step("Переход на страницу логина через ссылку 'Личный кабинет'")
    public void clickPersonalAccountLink() {
        personalAccountLinc.click();
    }




}
