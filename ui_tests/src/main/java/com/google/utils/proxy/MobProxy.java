package com.google.utils.proxy;

import com.codeborne.selenide.WebDriverRunner;
import com.test.data.constant.BaseEndpoint;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.HarEntry;

import java.util.List;
import java.util.stream.Collectors;

public class MobProxy {

    private BrowserMobProxy mobProxy;

    private MobProxy() {
        mobProxy = WebDriverRunner.getSelenideProxy().getProxy();
    }

    public final static MobProxy proxy = new MobProxy();

    public void createNewHar() {
        mobProxy.newHar();
    }

    public List<HarEntry> getRequests() {
        return mobProxy.getHar().getLog().getEntries();
    }

    public List<HarEntry> getApiRequest() {
        return getRequests().stream().filter(e -> e.getRequest().getUrl().startsWith(BaseEndpoint.baseApiUrl)).collect(Collectors.toList());
    }

    public List<HarEntry> getUiRequest() {
        return getRequests().stream().filter(e -> e.getRequest().getUrl().startsWith(BaseEndpoint.baseUIUrl)).collect(Collectors.toList());
    }

}
