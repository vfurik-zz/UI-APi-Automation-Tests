package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.pages.MainPage;
import com.google.utils.selenoid.SelenoidFactory;
import com.test.data.PropsController;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;


@Log4j
public class BaseUiTest {

    final private static boolean runIdDocker;

    static {
        log.info("Initialization Base UI Test ");
        String docker = System.getProperty("runInDocker");
        runIdDocker = docker != null && docker.equals("true");
        Configuration.baseUrl = PropsController.props.getUiUrlByEnv();
        Configuration.timeout = 12000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.pageLoadStrategy = "normal";
        Configuration.pollingInterval = 100;
        Configuration.fastSetValue = false;
        Configuration.clickViaJs = false;
        // Configuration.proxyEnabled = true;

        if (!runIdDocker) {
            log.info("Tests are started in Docker containers");
            Configuration.browser = SelenoidFactory.getSelenoidBrowser();
        } else {
            log.info("Tests are started locally");
            Configuration.browser = WebDriverRunner.CHROME;
            Configuration.browserSize = "1920x1080";
            System.setProperty("chromeoptions.args", "disable-infobars,disable-popup-blocking,--no-sandbox");
        }
    }

    @RegisterExtension
    static ScreenShooterExtension screen = new ScreenShooterExtension(true);

    @RegisterExtension
    static TextReportExtension reportWatcher = new TextReportExtension().onSucceededTest(true);


    @BeforeAll
    static void beforeAllClass() {
        SelenideLogger.addListener("Allure Selenide Listener", new AllureSelenide().savePageSource(true).screenshots(true));
    }

    @AfterEach
    void afterEach() {
        try {
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
            log.info("Clearing cookies and storage was successful");
        } catch (Exception e) {
            log.error("Error during clearing cookies and storage");
            log.error(e.getMessage());
        }
    }

    @Step
    protected MainPage openHomePage() {
        Selenide.open("/");
        return new MainPage();
    }

}
