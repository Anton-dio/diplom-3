package site.stellarburgers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import site.stellarburgers.model.LoginSection;
import site.stellarburgers.model.MainSection;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.*;
import static site.stellarburgers.generator.UserDataGenerator.*;
import static site.stellarburgers.generator.UserApiService.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Переходы на страницы")
public class GoToPageTest {

    MainSection mainPage;
    LoginSection loginPage;
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
        loginPage = page(LoginSection.class);
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
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        Assert.assertEquals(WebDriverRunner.url(), LoginSection.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickConstructorLink();
        Assert.assertEquals(WebDriverRunner.url(), MainSection.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickLogoLink();
        Assert.assertEquals(WebDriverRunner.url(), MainSection.MAIN_PAGE_URL);
    }
}