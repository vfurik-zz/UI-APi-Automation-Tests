package com.google.listeners.console_report;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

@Log4j
public class ConsoleLogReport implements AfterEachCallback {

    private boolean onFailedTest;
    private boolean onSuccessTest;
    private ConsoleLogHtml consoleLogHtml;

    public ConsoleLogReport() {
        onFailedTest = true;
        onSuccessTest = true;
        consoleLogHtml = new ConsoleLogHtml();
    }

    public ConsoleLogReport onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public ConsoleLogReport onSuccessTest(boolean onSuccessTest) {
        this.onSuccessTest = onSuccessTest;
        return this;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        List<LogEntry> logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
        consoleLogHtml.generateTable(logs);
        log.info("Console logs was attached");
    }
}
