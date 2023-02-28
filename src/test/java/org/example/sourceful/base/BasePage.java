package org.example.sourceful.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;
    private static final int FLUENT_WAIT = 30;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(FLUENT_WAIT));
        this.js = (JavascriptExecutor) driver;
    }

    protected final <T> void click(T locator) {
        getClickableElement(locator).click();
    }

    protected final <T> void jsClick(T locator) {
        js.executeScript("arguments[0].click();", getVisibleElement(locator));
    }

    protected final <T> void type(T locator, CharSequence... text) {
        getClickableElement(locator).sendKeys(text);
    }

    protected final <T> String getText(T locator) {
        return getVisibleElement(locator).getText();
    }

    protected final <T> void submitForm(T locator) {
        getClickableElement(locator).submit();
    }

    protected final void load(String url) {
        driver.get(url);
    }

    protected final void waitForPageTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    protected final <T> WebElement getClickableElement(T locator) {
        return wait.until(isBy(locator)
                ? ExpectedConditions.elementToBeClickable((By) locator)
                : ExpectedConditions.elementToBeClickable((WebElement) locator));
    }

    protected final <T> WebElement getVisibleElement(T locator) {
        return wait.until(isBy(locator)
                ? ExpectedConditions.visibilityOfElementLocated((By) locator)
                : ExpectedConditions.visibilityOf((WebElement) locator));
    }

    private <T> boolean isBy(T value) {
        return value.getClass().getName().contains("By");
    }
}
