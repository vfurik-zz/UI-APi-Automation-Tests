package com.google.utils.selenoid;

import com.codeborne.selenide.WebDriverProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidFirefoxWebDriverProvider implements WebDriverProvider {

    private final static String browserVersion;

    static {
        String envBrowserVersion = System.getProperty("browserVersion");
        if (StringUtils.isNoneEmpty(envBrowserVersion)) {
            browserVersion = envBrowserVersion;
        } else {
            browserVersion = "69.0";
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("69.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://selenoid:4444/wd/hub").toURL(),
                    capabilities
            );
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }


}
