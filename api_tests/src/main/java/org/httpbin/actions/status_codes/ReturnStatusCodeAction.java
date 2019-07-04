package org.httpbin.actions.status_codes;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.httpbin.actions.BaseAction;

public class ReturnStatusCodeAction extends BaseAction {

    @Step
    public ReturnStatusCodeAction getStatusCode(int code) {
        RestAssured.given(requestSpecBuilder
                .setBasePath("/status/{code}").build())
                .when()
                .pathParam("code", code)
                .post();
        return this;
    }
}
