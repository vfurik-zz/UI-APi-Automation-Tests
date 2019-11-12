package com.google.utils.report;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.nio.file.Files;


@Log4j
public class AllureUiUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScrenshot() {
        try {
            return Files.readAllBytes(Screenshots.takeScreenShotAsFile().toPath());
        } catch (IOException e) {
            log.error("Error during taking screnshot");
            return new byte[0];
        }
    }

    @Attachment(value = "Page sources", type = "text/html")
    public static String attachPageSources() {
        return WebDriverRunner.source();
    }

    @Attachment(value = "Video", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] attachVideo(byte[] video) {
        return video;
    }

    @Attachment(value = "Video link", type = "text/html")
    public static String attachVideoLink(String link){
        return link;
    }




}
