package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import site.stellarburgers.model.LoginSection;
import site.stellarburgers.model.MainSection;
import site.stellarburgers.model.RegistrationSection;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserDataGenerator.*;
import static site.stellarburgers.generator.UserApiService.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Регистрация")
public class RegisterUserTest {

    MainSection mainPage;
    LoginSection loginPage;
    RegistrationSection registrationPage;
    String newEmail;
    int userId;
    String token;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainSection.MAIN_PAGE_URL, MainSection.class);
        mainPage.clickSignInButton();

        loginPage = page(LoginSection.class);
        loginPage.clickRegisterLink();

        registrationPage = page(RegistrationSection.class);
        newEmail = getNewRandomEmail();

        // Создание пользователя перед тестом
        User user = new User(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);
        userId = createUser(user);

        // Логин пользователя для получения токена
        token = loginUser(user);
    }

    @After
    public void tearDown(){
        // Удаление пользователя после каждого теста
        deleteUser(userId, token);
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterAll() {
        closeNotChromeBrowser();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerUserSuccessfully() {
        registrationPage.register(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);
        loginPage.login(newEmail, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    public void registerUserWithShortPassword() {
        registrationPage.register(DEFAULT_NAME, newEmail, SHORT_PASSWORD);
        Assert.assertTrue(registrationPage.checkIsIncorrectPasswordTextVisible());
    }
}