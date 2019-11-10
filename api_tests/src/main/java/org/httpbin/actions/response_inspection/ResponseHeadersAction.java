package org.httpbin.actions.response_inspection;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.httpbin.actions.BaseAction;
import org.httpbin.models.response_inspection.ResponseHeaders;


public class ResponseHeadersAction extends BaseAction {

    private ResponseHeaders responseHeaders;

    @Step
    public ResponseHeadersAction returnSetOfRespHeaders(String freeForm) {
        this.responseHeaders = RestAssured
                .given(requestSpecBuilder.setBasePath("/response-headers").build())
                .when()
                .queryParam("freeform", freeForm)
                .get()
                .then()
                .extract()
                .response()
                .as(ResponseHeaders.class);
        return this;
    }

    @Step
    public ResponseHeadersAction verifyFreeForm(String freeForm) {
       // assertThat(this.responseHeaders.getFreeform()).isEqualTo(freeForm);
        return this;
    }

}
