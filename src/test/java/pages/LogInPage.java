package pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

public class LogInPage extends MobileMethods {
    public LogInPage(AndroidDriver driver) {super(driver);}

    private static final By LINE_TEXT = By.id("or_text");
    private static final By LINE_LEFT = By.id("or_line_left");
    private static final By ALERT_BODY = By.id("dialog_body");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By NEXT_BUTTON = By.id("button_text");
    private static final By LINE_RIGHT = By.id("or_line_right");
    private static final By LOGIN_FIELD = By.id("login_username");
    private static final By BRANDING_TEXT = By.id("branding_text");
    private static final By ALERT_FORM = By.id("dialog_root_view");
    private static final By REGISTER_BUTTON = By.id("log_in_button");
    private static final By LOGO_IMAGE = By.id("login_landing_logo");
    private static final By FACEBOOK_BUTTON = By.id("login_facebook");
    private static final By ALERT_TITLE = By.id("default_dialog_title");
    private static final By ALERT_SEND_BUTTON =  By.id("primary_button");
    private static final By FORGOT_BUTTON = By.id("login_forgot_button");
    private static final By ALERT_RETRY_BUTTON = By.id("negative_button");
    private static final By LANGUAGE_SELECTOR = By.id("language_selector_button");

    public enum Status {
        ENABLED("true"),
        DISABLED("false");

        public String description;
        Status(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    public enum Credentials {
        UNIVERSAL("test@test.ru"),
        ERROR("1");

        public String description;
        Credentials(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    public enum Password {
        CORRECT("Qwer1234"),
        INCORRECT("qwer");

        public String description;
        Password(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Отображение элементов формы входа")
    public void viewLoginCard() {
        waitForElement(LOGIN_FIELD);
        check(LINE_TEXT);
        check(LINE_LEFT);
        check(LINE_RIGHT);
        check(LOGO_IMAGE);
        check(BRANDING_TEXT);
        check(FORGOT_BUTTON);
        check(PASSWORD_FIELD);
        check(REGISTER_BUTTON);
        check(FACEBOOK_BUTTON);
        check(LANGUAGE_SELECTOR);
        equalText(LINE_TEXT, "OR");
        equalText(NEXT_BUTTON, "Log In");
        equalText(PASSWORD_FIELD, "Password");
        equalText(FACEBOOK_BUTTON, "Log in with Facebook");
        equalText(BRANDING_TEXT, "Instagram from Facebook");
        equalText(LANGUAGE_SELECTOR, "English (United States)");
        equalText(LOGIN_FIELD, "Phone number, email or username");
        equalText(REGISTER_BUTTON, "Don't have an account? Sign up.");
        equalText(FORGOT_BUTTON, "Forgot your login details? Get help signing in.");
    }

    @Step("Проверка статуса кнопки входа")
    public void checkStatusLogInButton(Status status) {
        waitForElement(NEXT_BUTTON);
        equalStatus(NEXT_BUTTON, status.description);
    }

    @Step("Заполнение формы авторизации")
    public LogInPage fillLogIn(Credentials login, Password password) {
        waitForElement(LOGIN_FIELD);
        type(LOGIN_FIELD, login.description);
        type(PASSWORD_FIELD, password.description);
        return this;
    }

    @Step("Сохранение формы авторизации")
    public LogInPage saveForm() {
        click(NEXT_BUTTON);
        return this;
    }

    @Step("Отображение элементов алерта формы авторизации")
    public LogInPage viewAlertIncorrectAuth() {
        waitForElement(ALERT_FORM);
        equalText(ALERT_RETRY_BUTTON, "Try Again");
        equalText(ALERT_TITLE, "Incorrect password");
        equalText(ALERT_BODY, "The password you entered is incorrect. Please try again.");
        return this;
    }

    @Step("Переход на форму - Помощь при входе")
    public void openHelpLogInForm() {
        waitForElement(FORGOT_BUTTON);
        click(FORGOT_BUTTON);
    }

    @Step("Очистка полей формы")
    public LogInPage closeAlertAndClearField() {
        click(ALERT_RETRY_BUTTON);
        clear(LOGIN_FIELD);
        clear(PASSWORD_FIELD);
        return this;
    }

    @Step("Отображение алерта - Восстановление пароля")
    public void viewAlertRecoveryPassword() {
        equalText(ALERT_SEND_BUTTON, "Send Email");
        equalText(ALERT_RETRY_BUTTON, "Try Again");
        equalText(ALERT_TITLE, "Forgotten password?");
        equalText(ALERT_BODY, "We can send you an email to help you get back into your account.");
    }

}
