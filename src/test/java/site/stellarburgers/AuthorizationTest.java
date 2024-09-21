package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import site.stellarburgers.model.*;

import static com.codeborne.selenide.Selenide.*;
import static site.stellarburgers.Browser.browserChoice;
import static site.stellarburgers.Browser.closeNotChromeBrowser;
import static site.stellarburgers.generator.UserGenerator.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Авторизация")
public class AuthorizationTest {

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    PersonalAccountPage personalAccountPage;

    @BeforeClass
    public static void beforeAll() {
        browserChoice();
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
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
