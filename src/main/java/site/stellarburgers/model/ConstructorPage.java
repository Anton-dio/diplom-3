package site.stellarburgers.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    // локатор таба "Булки"
    @FindBy(how = How.XPATH, using = "//div[span[text()='Булки']]")
    private SelenideElement  bunTab;

    // локатор таба "Соусы"
    @FindBy(how = How.XPATH, using = "//div[span[text()='Соусы']]")
    private SelenideElement sauceTab;

    // локатор таба "Начинки"
    @FindBy(how = How.XPATH, using = "//div[span[text()='Начинки']]")
    private SelenideElement fillingTab;

    public void clickSauceTab() {
        sauceTab.click();
    }

    public void clickFillingTab() {
        fillingTab.click();
    }

    public boolean checkIsBunTabSelected() {

        return  bunTab.getAttribute("class").contains("current");
    }

    public boolean checkIsSauceTabSelected() {

        return sauceTab.getAttribute("class").contains("current");
    }

    public boolean checkIsFillingTabSelected() {
        return fillingTab.getAttribute("class").contains("current");
    }
}
