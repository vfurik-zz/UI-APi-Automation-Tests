package org.httpbin.actions;

import com.test.data.constant.BaseEndpoint;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class BaseAction {

    static {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                //  .expectContentType(ContentType.JSON)
                .build();
    }

    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    protected BaseAction() {
        requestSpecBuilder
                .setBaseUri(BaseEndpoint.baseApiUrl)
                .setRelaxedHTTPSValidation()
                .setUrlEncodingEnabled(true)
                .log(LogDetail.ALL)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON);
    }

}
