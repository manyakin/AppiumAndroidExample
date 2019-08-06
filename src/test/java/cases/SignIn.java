package cases;

import io.qameta.allure.Feature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignInPage;
import settings.MobileSettings;

public class SignIn extends MobileSettings {

    @BeforeMethod(description = "Инициализация страниц")
    public void preparing() {
        objHome = new HomePage(driver);
        objSignIn = new SignInPage(driver);
    }

    @Feature("Регистрация")
    @Test(description = "Проверка блокировки кнопки регистрации - Заблокирована")
    public void checkDisabledSignInButton() {
        objHome.openSignInForm();
        objSignIn.checkStatusSignInButton(SignInPage.Status.DISABLED);
    }

    @Feature("Регистрация")
    @Test(description = "Проверка блокировки кнопки регистрации - Разблокирована")
    public void checkEnabledSignInButton() {
        objHome.openSignInForm();
        objSignIn.fillPhone(SignInPage.Credentials.PHONE)
                 .checkStatusSignInButton(SignInPage.Status.ENABLED);
    }

    @Feature("Регистрация")
    @Test(description = "Отображение формы - Регистрация с телефоном")
    public void checkSignInPhoneForm() {
        objHome.openSignInForm();
        objSignIn.viewCardWithPhone();
    }

    @Feature("Регистрация")
    @Test(description = "Отображение формы - Регистрация с почтой")
    public void checkSignInMailForm() {
        objHome.openSignInForm();
        objSignIn.selectMailTab()
                 .viewCardWithMail();
    }

    @Feature("Регистрация")
    @Test(description = "Отображение ошибки валидации поля - Телефон")
    public void checkValidatePhoneField() {
        objHome.openSignInForm();
        objSignIn.fillPhone(SignInPage.Credentials.ERROR_PHONE)
                 .saveForm()
                 .validatePhoneField();
    }

    @Feature("Регистрация")
    @Test(description = "Отображение ошибки валидации поля - Почта")
    public void checkValidateMailField() {
        objHome.openSignInForm();
        objSignIn.selectMailTab()
                 .fillMail(SignInPage.Credentials.ERROR_MAIL)
                 .saveForm()
                 .validateMailField();
    }

    @Feature("Регистрация")
    @Test(description = "Очистка поля телефона через кнопку сброса")
    public void checkClearPhoneField() {
        objHome.openSignInForm();
        objSignIn.fillPhone(SignInPage.Credentials.PHONE)
                 .clearPhoneFieldButton();
    }

    @Feature("Регистрация")
    @Test(description = "Очистка поля почты через кнопку сброса")
    public void checkClearMailField() {
        objHome.openSignInForm();
        objSignIn.selectMailTab()
                 .fillMail(SignInPage.Credentials.MAIL)
                 .clearMailFieldButton();
    }

    @Feature("Регистрация")
    @Test(description = "Смена телефонного кода для номера телефона")
    public void checkChangeMaskPhoneCode() {
        objHome.openSignInForm();
        objSignIn.changeMaskPhoneCode();
    }

    @Feature("Регистрация")
    @Test(description = "Отображение формы - Ввод имени и пароля")
    public void checkPersonalInfoForm() {
        objHome.openSignInForm();
        objSignIn.selectMailTab()
                 .fillMail(SignInPage.Credentials.RANDOM)
                 .saveForm()
                 .fillSignInMail();
    }

}
