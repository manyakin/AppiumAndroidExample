package settings;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MobileMethods extends MobileSettings {

    public MobileMethods(AndroidDriver driver) {this.driver = driver;}

    protected void clear(final By by) {
        driver.findElement(by).clear();
    }

    protected void click(final By by) {
        driver.findElement(by).click();
    }

    protected boolean isElementPresent(final By by) {
        List presentLoad = driver.findElements(by);
        return presentLoad.size() != 0;
    }

    protected void type(final By by, final String text) {
        driver.findElement(by).sendKeys(text);
    }

    protected void waitForElement(final By by) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void check(final By element) {
        Assert.assertTrue(driver.findElement(element).isDisplayed(), "Не отображен элемент " + element + "");
    }

    protected void equalText(final By element, final String text) {
        waitForElement(element);
        String getText = driver.findElement(element).getText();
        Assert.assertTrue(getText.contains(text), "Не совпадает текст " + getText + " и результат " + text + "");
    }

    protected void equalDescription(final By element, final String text) {
        waitForElement(element);
        String getDesc = driver.findElement(element).getAttribute("content-desc");
        Assert.assertTrue(getDesc.contains(text), "Не совпадает описание " + getDesc + " и результат " + text + "");
    }

    protected void equalStatus(final By element, final String status) {
        String getAttribute = driver.findElement(element).getAttribute("enabled");
        Assert.assertTrue(getAttribute.contains(status), "Не совпадает статус " + getAttribute + " и результат " + status + "");
    }

}
