package com.google.utils.seleniod_api;

import com.test.data.PropsController;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j;

@Log4j
public class SelenoidAction {

    @Step
    public byte[] getVideo(String sessionId) {
        for (int i = 0; i < 10; i++) {
            Response videoAction = getVideoAction(sessionId);
            if (videoAction.statusCode() != 200) {
                log.warn(String.format("Can not find video with id: %s", sessionId));
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return videoAction.asByteArray();
            }
        }
        log.warn("Video was not attached");
        return new byte[0];
    }


    @Step
    private Response getVideoAction(String sessionId) {
        return RestAssured.given(new RequestSpecBuilder()
                .setBasePath(PropsController.props.getSelenoidUrl())
                .setBasePath(String.format("/video/%s.mp4", sessionId))
                .build())
                .when()
                .get();
    }


}
