package com.google.listeners.html_report;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CustomHtmlReportExtension implements BeforeEachCallback, AfterEachCallback, AfterAllCallback {

    public CustomHtmlReportExtension() {
        htmlReport = new HtmlReport();
        onFailedTest = true;
        onSuccessTest = false;
    }

    private HtmlReport htmlReport;
    private boolean onFailedTest;
    private boolean onSuccessTest;

    public CustomHtmlReportExtension onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public CustomHtmlReportExtension onSuccessTest(boolean onSuccessTest) {
        this.onSuccessTest = onSuccessTest;
        return this;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        if (onFailedTest || onSuccessTest) {
            this.htmlReport.start();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        this.htmlReport.finish();
    }

    @Override
    public void afterAll(ExtensionContext context) {
        this.htmlReport.clean();
    }

}
