package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.listeners.CustomLogEventListener;
import com.google.pages.HomePage;
import com.test.data.PropsController;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;


@Log4j
public class BaseUiTest {

    static {
       // Selenide.open("about:blank");
        log.info("Initialization Base UI Test ");
        Configuration.baseUrl = PropsController.props.getUiUrlByEnv();
        Configuration.timeout = 6000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.pageLoadStrategy = "none";
        Configuration.pollingInterval = 100;
        //Configuration.browser = "com.google.utils.SelenoidWebDriverProvider"; use it for run tests using docker container
        System.setProperty("chromeoptions.args", "disable-infobars,disable-popup-blocking,--no-sandbox");
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.browserSize = "1280x1024";

    }

    @BeforeClass
    public static void beforeAllClass(){
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide().savePageSource(true).screenshots(true));
        SelenideLogger.addListener("Custom Event Listener", new CustomLogEventListener());
    }

    @Rule
    public TextReport textReport = new TextReport();

    @After
    public void afterEach() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    //    @Step
    protected HomePage openHomePage() {
        Selenide.open("/");
        return new HomePage();
    }

}
