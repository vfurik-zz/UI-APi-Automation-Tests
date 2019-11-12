package com.google.listeners.network_report;

import com.google.utils.proxy.MobProxy;
import com.test.data.utils.AllureUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class NetworkReport implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        AllureUtils.attachNetworkLog(new NetworkHtmlReport().generateTable(MobProxy.proxy.getUiRequest()));
        AllureUtils.attachNetworkLog(new NetworkHtmlReport().generateTable(MobProxy.proxy.getApiRequest()));
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        MobProxy.proxy.createNewHar();
    }

}
