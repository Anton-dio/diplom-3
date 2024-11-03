package site.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import site.stellarburgers.model.ConstructorSection;
import site.stellarburgers.model.MainSection;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserDataGenerator.*;
import static site.stellarburgers.generator.UserApiService.*;

@DisplayName("Вкладки конструктора")
public class SectionsTest {

    MainSection mainPage;
    ConstructorSection constructorPage;
    private static int userId;
    private static String token;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
        User user = new User(DEFAULT_NAME, getNewRandomEmail(), DEFAULT_PASSWORD);
        userId = createUser(user);
        token = loginUser(user);
        Assert.assertNotEquals(0, userId);
    }

    @Before
    public void setUp() {
        mainPage = open(MainSection.MAIN_PAGE_URL, MainSection.class);
        constructorPage = page(ConstructorSection.class);
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        deleteUser(userId, token);
        ValidatableResponse response = deleteUser(userId, token);
        Assert.assertEquals(200, response.extract().statusCode());
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