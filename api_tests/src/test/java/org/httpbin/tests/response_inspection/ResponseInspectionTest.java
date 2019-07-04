package org.httpbin.tests.response_inspection;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.naming.TestCaseName;
import org.httpbin.actions.response_inspection.ResponseHeadersAction;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ResponseInspectionTest {

    @Test
    @FileParameters("src/main/resources/responseHeadersData.csv")
    @TestCaseName("verify response header test for value {0}")
    public void responseHeadersTest(String value) {
        new ResponseHeadersAction().returnSetOfRespHeaders(value).verifyFreeForm(value);
    }

}
