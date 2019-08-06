package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HelpLogInPage;
import pages.HomePage;
import pages.LogInPage;
import settings.MobileSettings;

import static pages.HomePage.Language.RUSSIAN;

public class Other extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objLogIn = new LogInPage(driver);
        objHelp = new HelpLogInPage(driver);
    }

    @Feature("Прочее")
    @Test(description = "Отображение формы - Стартовый экран")
    public void viewHomePageForm() {
        objHome.viewHomeCard();
    }

    @Feature("Прочее")
    @Test(description = "Проверка корректной смены языка приложения")
    public void checkChangeLanguage() {
        objHome.openLanguageForm()
               .fillLanguage(RUSSIAN)
               .viewSelectLanguage();
    }

    @Feature("Помощь при входе")
    @Test(description = "Отображение формы - Помощь при входе")
    public void checkHelpLogInForm() {
        objHome.openLogInForm();
        objLogIn.openHelpLogInForm();
        objHelp.viewHelpLogInForm();
    }

    @Feature("Помощь при входе")
    @Test(description = "Отображение ошибки валидации формы - Помощь при входе")
    public void checkValidateHelpLogInForm() {
        objHome.openLogInForm();
        objLogIn.openHelpLogInForm();
        objHelp.fillLogin(HelpLogInPage.Credentials.ERROR)
               .viewValidateHelpLogInForm();
    }

    @Feature("Помощь при входе")
    @Test(description = "Отображение элементов формы - Подтверждение помощи при входе")
    public void checkConfirmHelpLogInForm() {
        objHome.openLogInForm();
        objLogIn.openHelpLogInForm();
        objHelp.fillLogin(HelpLogInPage.Credentials.CURRENT)
               .viewConfirmHelpLogInForm();
    }

    @Feature("Прочее")
    @Test(description = "Отображение алерта - Восстановление пароля")
    public void checkAlertRecoveryPassword() {
        objHome.openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.UNIVERSAL, LogInPage.Password.INCORRECT)
                .saveForm()
                .viewAlertIncorrectAuth()
                .closeAlertAndClearField()
                .fillLogIn(LogInPage.Credentials.UNIVERSAL, LogInPage.Password.INCORRECT)
                .saveForm()
                .viewAlertRecoveryPassword();
    }

}
