package site.stellarburgers.generator;

public class UserGenerator {

    public static final String DEFAULT_NAME = "Vsevolod";
    public static final String DEFAULT_EMAIL = "stdr@test.ru";

    public static final String WORKING_EMAIL = "sevamukh@mail.ru";
    public static final String DEFAULT_PASSWORD = "pwd123";
    public static final String SHORT_PASSWORD = "123";

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}
