package com.google.listeners.video;

import com.codeborne.selenide.WebDriverRunner;
import com.google.BaseUiTest;
import com.google.utils.report.AllureUiUtils;
import com.test.data.PropsController;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.remote.RemoteWebDriver;

@Log4j
public class VideoReport implements AfterEachCallback {

    public VideoReport() {
        this.onFailedTest = true;
        this.onSuccessTest = false;
    }

    private boolean onFailedTest;

    private boolean onSuccessTest;

    public VideoReport onFailedTest(boolean onFailedTest) {
        this.onFailedTest = onFailedTest;
        return this;
    }

    public VideoReport onSuccessTest(boolean onSuccessTest) {
        this.onSuccessTest = onSuccessTest;
        return this;
    }

    @Override
    public void afterEach(ExtensionContext context) {
        if (BaseUiTest.runIdDocker) {
            String sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString();

            String videoUrl = String.format("%s/video/%s.mp4", PropsController.props.getSelenoidUrl(), sessionId);
            String format = String.format("<html><p><a href=\"%s\">%s</a></p></html>", videoUrl, context.getDisplayName());
            AllureUiUtils.attachVideoLink(format);

/*            byte[] video = new SelenoidAction().getVideo(sessionId);
            if (onFailedTest && context.getExecutionException().isPresent()) {
                AllureUiUtils.attachVideo(video);
            } else if (onSuccessTest) {
                AllureUiUtils.attachVideo(video);
            }*/
        }
    }
}
