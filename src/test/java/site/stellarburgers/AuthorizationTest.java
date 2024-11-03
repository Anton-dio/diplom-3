package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import site.stellarburgers.model.*;
import site.stellarburgers.model.User;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserDataGenerator.*;
import static site.stellarburgers.generator.UserApiService.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Авторизация")
public class AuthorizationTest {

    MainSection mainPage;
    LoginSection loginPage;
    RegistrationSection registrationPage;
    PasswordRecoverySection passwordRecoveryPage;
    PersonalAccountSection personalAccountPage;

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
        registrationPage = page(RegistrationSection.class);
        passwordRecoveryPage = page(PasswordRecoverySection.class);
        personalAccountPage = page(PersonalAccountSection.class);
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void signInBySignInButtonOnMainPage() {
        mainPage.clickSignInButton();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void signInByPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void signInBySignInButtonOnRegistrationPage() {
        mainPage.clickSignInButton();
        loginPage.clickRegisterLink();
        registrationPage.clickSignInLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void signInBySignInButtonOnPasswordRecoveryPage() {
        mainPage.clickSignInButton();
        loginPage.clickPasswordRecoveryLink();
        passwordRecoveryPage.clickSignInLink();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void signOut() {
        mainPage.clickSignInButton();
        loginPage.login(WORKING_EMAIL, DEFAULT_PASSWORD);
        mainPage.clickPersonalAccountLink();
        personalAccountPage.clickSignOutButton();
        loginPage.clickLogoLink();
        Assert.assertTrue(mainPage.checkIsSignInButtonEnabled());
    }
}