package site.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import site.stellarburgers.model.ConstructorPage;
import site.stellarburgers.model.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;

@DisplayName("Вкладки конструктора")
public class SectionsTest {

    MainPage mainPage;
    ConstructorPage constructorPage;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        constructorPage = page(ConstructorPage.class);
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    @DisplayName("Проверка работы вкладки «Булки»")
    @Description("Тест проверяет, что вкладка «Булки» выбрана по умолчанию.")
    public void bunTabValidWorking() {
        Assert.assertTrue(constructorPage.checkIsBunTabSelected());
    }

    @Test
    @DisplayName("Проверка работы вкладки «Соусы»")
    @Description("Тест проверяет, что вкладка «Соусы» выбирается корректно.")
    public void sauceTabValidWorking() {
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.checkIsSauceTabSelected());
    }

    @Test
    @DisplayName("Проверка работы вкладки «Начинки»")
    @Description("Тест проверяет, что вкладка «Начинки» выбирается корректно.")
    public void fillingTabValidWorking() {
        constructorPage.clickFillingTab();
        Assert.assertTrue(constructorPage.checkIsFillingTabSelected());
    }
}