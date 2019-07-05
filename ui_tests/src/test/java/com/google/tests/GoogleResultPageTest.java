package com.google.tests;

import com.google.BaseUiTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GoogleResultPageTest extends BaseUiTest {

    @Test
    @Parameters({
            "Selenide",
            "Selenium",
            "SelenideSelenium"  //test should fails
    })
    public void verifyResultPage(String text) {
        openHomePage().search(text).verifyFirstResult(text);
    }

}