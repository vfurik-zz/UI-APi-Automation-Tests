package com.google.utils.selenoid;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class SelenoidFactory {

    public final static String browser;

    static {
        String envBrowser = System.getProperty("browser");
        if (StringUtils.isNoneEmpty(envBrowser) && envBrowser.equalsIgnoreCase("firefox")) {
            browser = "firefox";
        } else {
            browser = "chrome"; // Use chrome as default browser
        }
    }

    public String getSelenoidBrowser() {
        return browser.equals("firefox") ? SelenoidFirefoxWebDriverProvider.class.getName() : SelenoidChromeWebDriverProvider.class.getName();
    }

}
