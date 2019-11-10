package com.google.utils.selenoid;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidChromeWebDriverProvider implements WebDriverProvider {

    private final static String browserVersion;

    static {
        String envBrowserVersion = System.getProperty("browserVersion");
        if (StringUtils.isNoneEmpty(envBrowserVersion)) {
            browserVersion = envBrowserVersion;
        } else {
            browserVersion = "78.0";
        }
    }

    /**
     * Implementation of custom webdriver provider for running ui tests using Selenoid on chrome
     *
     * @param desiredCapabilities
     * @return
     */

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion(browserVersion);
        browser.setCapability("enableVNC", true);
        browser.setCapability("enableVideo", true);
        browser.setCapability("videoName", this.getClass().getName() + "1234567111.mp4");
        browser.setCapability("name", "Chrome version: " + browserVersion);

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("disable-popup-blocking");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        browser.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1920, 1080));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
