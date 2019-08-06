package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

public class HelpLogInPage extends MobileMethods {
    public HelpLogInPage(AndroidDriver driver) {super(driver);}

    private static final By LINE_TEXT = By.id("or_text");
    private static final By LINE_LEFT = By.id("or_line_left");
    private static final By HEAD_TITLE = By.id("field_title");
    private static final By NEXT_BUTTON = By.id("button_text");
    private static final By LINE_REIGHT = By.id("or_line_right");
    private static final By HELP_DETAILED = By.id("help_center");
    private static final By FACEBOOK_BUTTON = By.id("login_facebook");
    private static final By LOGIN_HINT_DETAILED = By.id("field_detail");
    private static final By BACK_BUTTON = By.id("action_bar_button_back");
    private static final By HELP_FORM = By.id("action_bar_textview_title");
    private static final By LOGIN_FIELD = By.id("fragment_lookup_edittext");
    private static final By CONFIRM_HELP_FORM = By.id("action_bar_textview_title");
    private static final By CONFIRM_MAIL_BUTTON = By.id("fragment_user_password_recovery_button_email_reset");
    private static final By ERROR_HINT = MobileBy.AndroidUIAutomator("new UiSelector().text(\"No users found.\")");

    public enum Credentials {
        CURRENT("test@test.ru"),
        ERROR("1");

        public String description;
        Credentials(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Отображение элементов формы - Помощь при входе")
    public void viewHelpLogInForm() {
        waitForElement(LINE_TEXT);
        check(LINE_LEFT);
        check(LINE_REIGHT);
        equalText(NEXT_BUTTON, "Next");
        equalText(HELP_FORM, "Login Help");
        equalText(HEAD_TITLE, "Find Your Account");
        equalText(FACEBOOK_BUTTON, "Log in with Facebook");
        equalText(LOGIN_FIELD, "Username, email or phone");
        equalText(HELP_DETAILED, "For more help, visit the Instagram Help Center.");
        equalText(LOGIN_HINT_DETAILED, "Enter your Instagram username or the email or phone number linked to your account.");
    }

    @Step("Отображение ошибки валидации формы - Помощь при входе")
    public void viewValidateHelpLogInForm() {
        check(ERROR_HINT);
    }

    @Step("Заполнение логина")
    public HelpLogInPage fillLogin(Credentials login) {
        type(LOGIN_FIELD, login.description);
        click(NEXT_BUTTON);
        return this;
    }

    @Step("Отображение элементов формы - Подтверждение помощи при входе")
    public void viewConfirmHelpLogInForm() {
        waitForElement(BACK_BUTTON);
        equalText(CONFIRM_MAIL_BUTTON, "Send an Email");
        equalText(CONFIRM_HELP_FORM, "Access Your Account");
    }

}
