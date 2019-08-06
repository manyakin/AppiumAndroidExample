package pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

import java.util.Random;

public class SignInPage extends MobileMethods {
    public SignInPage(AndroidDriver driver) {super(driver);}

    private static final Random random = new Random();
    private static final By PHONE_TAB = By.id("left_tab");
    private static final By MAIL_TAB = By.id("right_tab");
    private static final By SEARCH_FIELD = By.id("search");
    private static final By LOGO_IMAGE = By.id("image_icon");
    private static final By MAIL_FIELD = By.id("email_field");
    private static final By PHONE_FIELD = By.id("phone_field");
    private static final By NEXT_BUTTON = By.id("button_text");
    private static final By TAB_BLOCK = By.id("tab_selection");
    private static final By PERSONAL_NAME = By.id("full_name");
    private static final By PERSONAL_FORM = By.id("field_title");
    private static final By PERSONAL_PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("log_in_button");
    private static final By PHONE_SMS_HINT = By.id("sms_consent");
    private static final By PHONE_MASK = By.id("country_code_picker");
    private static final By PHONE_CODE_LIST = By.id("country_code_list");
    private static final By PERMISSION_BODY = By.id("permission_message");
    private static final By PERMISSION_ACCEPT = By.id("android:id/button2");
    private static final By MAIL_CLEAR_BUTTON = By.id("email_clear_button");
    private static final By PHONE_CLEAR_BUTTON = By.id("phone_clear_button");
    private static final By PHONE_CODE_RESULT = By.id("row_simple_text_textview");
    private static final By PERSONAL_REMEMBER = By.id("remember_password_checkbox");
    private static final By PERSONAL_WITHOUT_CONTACTS = By.id("continue_without_ci");
    private static final By PERSONAL_CONTACTS_HINT = By.id("search_contact_explanation");
    private static final By PHONE_VALIDATE = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Looks like your phone number may be incorrect. Please try entering your full number, including the country code.\")");
    private static final By MAIL_VALIDATE = MobileBy.AndroidUIAutomator("new UiSelector().text(\"Please enter a valid email.\")");

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
        MAIL("test@test.ru"),
        PHONE("9609948474"),
        ERROR_PHONE("1"),
        ERROR_MAIL("a"),
        RANDOM("test@iddqd" + random.nextInt() + ".ru");

        public String description;
        Credentials(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Проверка статуса кнопки входа")
    public void checkStatusSignInButton(Status status) {
        equalStatus(NEXT_BUTTON, status.description);
    }

    @Step("Отклонение рандомного запроса на права для приложения")
    void checkPermission() {
        waitForElement(NEXT_BUTTON);
        if (isElementPresent(PERMISSION_BODY)) {
            click(PERMISSION_ACCEPT);
        }
    }

    @Step("Отображение формы регистрации с телефоном")
    public void viewCardWithPhone() {
        waitForElement(LOGO_IMAGE);
        check(PHONE_TAB);
        check(TAB_BLOCK);
        check(PHONE_MASK);
        check(PHONE_FIELD);
        check(PHONE_SMS_HINT);
        check(NEXT_BUTTON);
        check(LOGIN_BUTTON);
        equalText(NEXT_BUTTON, "Next");
        equalText(PHONE_MASK, "US +1");
        equalText(PHONE_FIELD, "Phone");
        equalDescription(PHONE_TAB, "Phone");
        equalText(LOGIN_BUTTON, "Already have an account? Log in.");
        equalText(PHONE_SMS_HINT, "You may receive SMS updates from Instagram and can opt out at any time.");
    }

    @Step("Выбор формы почты")
    public SignInPage selectMailTab() {
        waitForElement(MAIL_TAB);
        click(MAIL_TAB);
        return this;
    }

    @Step("Отображение формы регистрации с почтой")
    public void viewCardWithMail() {
        check(TAB_BLOCK);
        check(MAIL_FIELD);
        check(NEXT_BUTTON);
        check(LOGIN_BUTTON);
        equalText(MAIL_FIELD, "Email");
        equalText(NEXT_BUTTON, "Next");
        equalDescription(MAIL_TAB, "Email");
        equalText(LOGIN_BUTTON, "Already have an account? Log in.");
    }

    @Step("Отображение ошибки валидации поля - Почта")
    public void validateMailField() {
        check(MAIL_VALIDATE);
    }

    @Step("Сохранение формы")
    public SignInPage saveForm() {
        waitForElement(NEXT_BUTTON);
        click(NEXT_BUTTON);
        return this;
    }

    @Step("Заполнение почты")
    public SignInPage fillMail(Credentials login) {
        waitForElement(MAIL_FIELD);
        type(MAIL_FIELD, login.description);
        return this;
    }

    @Step("Очистка поля почты через кнопку сброса")
    public void clearMailFieldButton() {
        click(MAIL_CLEAR_BUTTON);
        equalText(MAIL_FIELD, "Email");
    }

    @Step("Отображение формы - Ввод имени и пароля")
    public void fillSignInMail() {
        equalText(PERSONAL_NAME, "Full name");
        equalText(PERSONAL_PASSWORD, "Password");
        equalText(PERSONAL_FORM, "NAME AND PASSWORD");
        equalText(PERSONAL_REMEMBER, "Save Password");
        equalText(NEXT_BUTTON, "Continue and Sync Contacts");
        equalText(PERSONAL_WITHOUT_CONTACTS, "Continue Without Syncing Contacts");
        equalText(PERSONAL_CONTACTS_HINT, "Your contacts will be periodically synced and stored on Instagram servers to help you and others find friends, and to help us provide a better service. To remove contacts, go to Settings and disconnect. Learn More.");
    }

    @Step("Заполнение телефона")
    public SignInPage fillPhone(Credentials login) {
        waitForElement(PHONE_FIELD);
        type(PHONE_FIELD, login.description);
        return this;
    }

    @Step("Отображение ошибки валидации поля - Телефон")
    public void validatePhoneField() {
        check(PHONE_VALIDATE);
    }

    @Step("Очистка поля телефона через кнопку сброса")
    public void clearPhoneFieldButton() {
        click(PHONE_CLEAR_BUTTON);
        equalText(PHONE_FIELD, "Phone");
    }

    @Step("Очистка поля почты через кнопку сброса")
    public void changeMaskPhoneCode() {
        click(PHONE_MASK);
        waitForElement(PHONE_CODE_LIST);
        type(SEARCH_FIELD, "Russia");
        waitForElement(PHONE_CODE_RESULT);
        click(PHONE_CODE_RESULT);
        equalText(PHONE_MASK, "RU +7");
    }

}
