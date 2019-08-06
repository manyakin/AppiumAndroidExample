package settings;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import pages.HelpLogInPage;
import pages.HomePage;
import pages.LogInPage;
import pages.SignInPage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static com.google.common.io.Files.toByteArray;


@Listeners({MobileSettings.class})
public class MobileSettings implements ITestListener {

    protected HomePage objHome;
    protected LogInPage objLogIn;
    protected SignInPage objSignIn;
    protected AndroidDriver driver;
    protected HelpLogInPage objHelp;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"udid", "port", "version"})
    public void setUp(final String udid, final Integer port, final String version) throws InterruptedException {

        String PACKAGE = "com.instagram.android";
        String ACTIVITY = "com.instagram.mainactivity.MainActivity";
        String APP = new File("src/test/resources/app/Instagram.apk").getAbsolutePath();

        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.APP, APP);
            capabilities.setCapability(MobileCapabilityType.UDID, udid);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
            capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, port);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ACTIVITY);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ACTIVITY);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
            Thread.sleep(2);

        } catch (MalformedURLException e) {
            Logger.getLogger("Сессия не запущена");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /* Создание скриншота */
    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreen() {
        try {
            File screenshot = driver.getScreenshotAs(OutputType.FILE);
            return toByteArray(screenshot);
        } catch (IOException e) {
            Logger.getLogger("Ошибка в методе makeScreen");
        }
        return new byte[0];
    }

    private AndroidDriver getDriver() {
        return driver;
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        driver = ((MobileSettings) currentClass).getDriver();
        makeScreen();
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
