package site.stellarburgers.generator;

public class UserDataGenerator {

    public static final String DEFAULT_NAME = "Anton";
    public static final String DEFAULT_EMAIL = "stdr@test.ru";
    public static final String WORKING_EMAIL = "zharkov@mail.ru";
    public static final String DEFAULT_PASSWORD = "pwd123";
    public static final String SHORT_PASSWORD = "4Qcdfggd";

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}