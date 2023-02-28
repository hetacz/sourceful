package org.example.sourceful.base;

public interface Loadable<T extends BasePage> {

    T load();
    T waitForPageToLoad();
}
