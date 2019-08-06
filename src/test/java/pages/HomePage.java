package pages;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import settings.MobileMethods;

public class HomePage extends MobileMethods {
    public HomePage(AndroidDriver driver) {super(driver);}

    private static final By LOGO_IMAGE = By.id("logo");
    private static final By ALERT_FORM = By.id("alertTitle");
    private static final By LOG_IN_BUTTON = By.id("log_in_button");
    private static final By BRANDING_TEXT = By.id("branding_text");
    private static final By LANGUAGE_SEARCH_FIELD = By.id("search");
    private static final By LANGUAGE_RESULT = By.id("language_name");
    private static final By LANGUAGE_LIST = By.id("language_locale_list");
    private static final By LANGUAGE_SELECTOR = By.id("language_selector_button");
    private static final By SIGN_UP_BUTTON = By.id("sign_up_with_email_or_phone");

    public enum Language {
        RUSSIAN("Russian"),
        ENGLISH("English");

        public String description;
        Language(String description) {
            this.description = description;
        }
        public String getDescription() {return description;}
    }

    @Step("Отображение элементов стартовой страницы")
    public void viewHomeCard() {
        check(LOGO_IMAGE);
        check(LOG_IN_BUTTON);
        check(BRANDING_TEXT);
        check(SIGN_UP_BUTTON);
        check(LANGUAGE_SELECTOR);
        equalText(LOG_IN_BUTTON, "Log In");
        equalText(SIGN_UP_BUTTON, "Create New Account");
        equalText(BRANDING_TEXT, "Instagram from Facebook");
        equalText(LANGUAGE_SELECTOR, "English (United States)");
    }

    @Step("Открытие формы выбора языка")
    public HomePage openLanguageForm() {
        click(LANGUAGE_SELECTOR);
        waitForElement(ALERT_FORM);
        return this;
    }

    @Step("Выбор языка для приложения")
    public HomePage fillLanguage(Language language) {
        type(LANGUAGE_SEARCH_FIELD, language.description);
        waitForElement(LANGUAGE_LIST);
        click(LANGUAGE_RESULT);
        return this;
    }

    @Step("Проверка отображения выбранного языка")
    public void viewSelectLanguage() {
        equalDescription(LANGUAGE_SELECTOR, "Выберите язык. Русский  .");
    }

    @Step("Переход на форму авторизации")
    public void openLogInForm() {
        waitForElement(LOG_IN_BUTTON);
        click(LOG_IN_BUTTON);
    }

    @Step("Переход на форму регистрации")
    public void openSignInForm() {
        click(SIGN_UP_BUTTON);
        objSignIn = new SignInPage(driver);
        objSignIn.checkPermission();
    }

}
