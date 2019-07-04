package org.httpbin.actions.request_inspection;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.httpbin.actions.BaseAction;
import org.httpbin.models.request_inspection.IpAddressResponse;

public class ReturnIpAddressAction extends BaseAction {

    @Step
    public IpAddressResponse getIp() {
        return RestAssured
                .given(requestSpecBuilder.setBasePath("/ip").build())
                .when()
                .get()
                .then()
                .extract()
                .response().as(IpAddressResponse.class);
    }


}
