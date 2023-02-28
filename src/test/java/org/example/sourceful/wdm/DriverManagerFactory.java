package org.example.sourceful.wdm;

import org.example.sourceful.base.BrowserType;
import org.example.sourceful.wdm.driver.ChromeDriverManager;
import org.example.sourceful.wdm.driver.EdgeDriverManager;
import org.example.sourceful.wdm.driver.FirefoxDriverManager;

public final class DriverManagerFactory {

    public static DriverManager getManager(BrowserType browserType) {
        return switch (browserType) {
            case CHROME -> new ChromeDriverManager();
            case FIREFOX -> new FirefoxDriverManager();
            case EDGE -> new EdgeDriverManager();
        };
    }
}
