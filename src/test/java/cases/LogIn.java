package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import settings.MobileSettings;

import static pages.LogInPage.Password.CORRECT;

public class LogIn extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objLogIn = new LogInPage(driver);
    }

    @Feature("Авторизация")
    @Test(description = "Отображение формы - Авторизация")
    public void checkLogInForm() {
        objHome.openLogInForm();
        objLogIn.viewLoginCard();
    }

    @Feature("Авторизация")
    @Test(description = "Проверка блокировки кнопки входа - Заблокирована")
    public void checkDisabledLogInButton() {
        objHome.openLogInForm();
        objLogIn.checkStatusLogInButton(LogInPage.Status.DISABLED);
    }

    @Feature("Авторизация")
    @Test(description = "Проверка блокировки кнопки входа - Разблокирована")
    public void checkEnabledLogInButton() {
        objHome.openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.UNIVERSAL, CORRECT)
                .checkStatusLogInButton(LogInPage.Status.ENABLED);
    }

    @Feature("Авторизация")
    @Test(description = "Проверка отображения алерта некорректной авторизации")
    public void checkAlertAtIncorrectAuth() {
        objHome.openLogInForm();
        objLogIn.fillLogIn(LogInPage.Credentials.UNIVERSAL, CORRECT)
                .saveForm()
                .viewAlertIncorrectAuth();
    }

}
