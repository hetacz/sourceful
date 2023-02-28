package org.example.sourceful.pages;

import org.example.sourceful.base.BasePage;
import org.example.sourceful.base.Loadable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPO extends BasePage implements Loadable<ContactPO> {

    private static final String URL = "https://sourceful.nl/nl/contact-pl/";
    private static final String TITLE = "Contact-pl - Sourceful ICT - Building future in financial services";
    private static final int SLEEP_TIME = 30_000;
    private final By nameFld = By.name("your-name");
    private final By emailFld = By.name("your-email");
    private final By subjectFld = By.name("your-subject");
    private final By messageFld = By.name("your-message");
    private final By iframe = By.xpath("//iframe[starts-with(@name, 'a-')]");
    private final By checkbox = By.cssSelector("div.recaptcha-checkbox-border");
    private final By form = By.cssSelector("form.init");
    private final By successDiv = By.cssSelector("div.wpcf7-response-output");

    public ContactPO(WebDriver driver) {
        super(driver);
    }

    @Override
    public ContactPO load() {
        load(URL);
        waitForPageToLoad();
        return this;
    }

    @Override
    public ContactPO waitForPageToLoad() {
        waitForPageTitle(TITLE);
        return this;
    }

    public ContactPO setName(String name) {
        type(nameFld, name);
        return this;
    }

    public ContactPO setEmail(String email) {
        type(emailFld, email);
        return this;
    }

    public ContactPO setSubject(String subject) {
        type(subjectFld, subject);
        return this;
    }

    public ContactPO setMessage(String message) {
        type(messageFld, message);
        return this;
    }

    public ContactPO clickRecaptcha() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        return this;
    }

    public ContactPO switchToDefaultContextAndWait() {
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public ContactPO submitForm() {
        driver.switchTo().defaultContent();
        submitForm(form);
        return this;
    }

    public ContactPO verifySuccessDivVisible() {
        getVisibleElement(successDiv);
        return this;
    }

    public String getSuccessDivText() {
        return getText(successDiv);
    }
}
