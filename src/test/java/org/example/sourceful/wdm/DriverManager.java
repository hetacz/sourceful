package org.example.sourceful.wdm;

import org.openqa.selenium.WebDriver;

public interface DriverManager {

    WebDriver createDriver();
    WebDriver createDriverHeadless();
}
