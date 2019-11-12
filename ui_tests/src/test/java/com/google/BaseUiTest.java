package com.google;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.listeners.console_report.ConsoleLogReport;
import com.google.listeners.html_report.CustomHtmlReportExtension;
import com.google.listeners.network_report.NetworkHtmlReport;
import com.google.listeners.network_report.NetworkReport;
import com.google.listeners.video.VideoReport;
import com.google.pages.MainPage;
import com.google.utils.selenoid.SelenoidChromeWebDriverProvider;
import com.test.data.PropsController;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;


@Log4j
public class BaseUiTest {

    final public static boolean runIdDocker = true;

    static {
        log.info("Initialization Base UI Test ");
        String docker = System.getProperty("runInDocker");
        //  runIdDocker = docker != null && docker.equals("true");
        Configuration.baseUrl = PropsController.props.getUiUrlByEnv();
        Configuration.timeout = 16000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.pageLoadStrategy = "normal";
        Configuration.pollingInterval = 100;
        Configuration.fastSetValue = false;
        Configuration.clickViaJs = false;
        Configuration.proxyEnabled = true;

        if (runIdDocker) {
            log.info("Tests are started in Docker containers");
            Configuration.browser = SelenoidChromeWebDriverProvider.class.getName();
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

    @RegisterExtension
    static CustomHtmlReportExtension htmlReportExtension = new CustomHtmlReportExtension().onSuccessTest(true);

    @RegisterExtension
    ConsoleLogReport consoleLogReport = new ConsoleLogReport();

    @RegisterExtension
    VideoReport videoReport = new VideoReport().onSuccessTest(false);

    @RegisterExtension
    NetworkReport networkHtmlReport = new NetworkReport();


    @BeforeAll
    static void beforeAllClass() {
        Selenide.open();
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
