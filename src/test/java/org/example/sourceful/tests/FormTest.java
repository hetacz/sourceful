package org.example.sourceful.tests;

import org.assertj.core.api.Assertions;
import org.example.sourceful.base.BaseTest;
import org.example.sourceful.pages.ContactPO;
import org.example.sourceful.utils.Generate;
import org.testng.annotations.Test;

public class FormTest extends BaseTest {

    private static final String THANK_YOU_MSG = "Dziękujemy, wiadomość została wysłana.";
    private static final String ASSERTION_ERR_MSG = "Thank you message is not displayed";

    @Test
    public void testForm() {
        String divText = new ContactPO(driver())
                .load()
                .setName(Generate.name())
                .setEmail(Generate.email())
                .setSubject(Generate.subject())
                .setMessage(Generate.message())
                .clickRecaptcha()
                .switchToDefaultContextAndWait()
                .submitForm()
                .verifySuccessDivVisible()
                .getSuccessDivText();
        Assertions.assertThat(divText).as(ASSERTION_ERR_MSG).isEqualTo(THANK_YOU_MSG);
    }
}
