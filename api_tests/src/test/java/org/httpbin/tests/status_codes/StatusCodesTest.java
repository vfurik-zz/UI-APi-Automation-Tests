package org.httpbin.tests.status_codes;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.httpbin.actions.status_codes.ReturnStatusCodeAction;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class StatusCodesTest {

    private List<Integer> provideTestData() {
        return new ArrayList<>(Arrays.asList(
                200,
                201 // this test should fails. (Expected status code is 200)
        ));
    }

    @Test
    @Parameters(method = "provideTestData")
    @TestCaseName(value = "verify return status code [{0}]")
    public void returnStatusCodeTest(int code) {
        new ReturnStatusCodeAction().getStatusCode(code);
    }

}
