package org.httpbin.tests.request_inspection;

import org.httpbin.actions.request_inspection.ReturnIpAddressAction;
import org.junit.Test;


public class RequestInspectionTest {

    @Test
    public void getIpAddressTest() {
        new ReturnIpAddressAction().getIp();
    }

}
