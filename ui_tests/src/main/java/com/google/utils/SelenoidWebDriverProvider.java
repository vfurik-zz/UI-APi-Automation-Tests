package com.google.utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {

    /**
     * Implementation of custom webdriver provider for running ui tests using Selenoid
     * @param desiredCapabilities
     * @return
     */

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("75.0");
        browser.setCapability("enableVNC", true);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        browser.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1280, 1024));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
