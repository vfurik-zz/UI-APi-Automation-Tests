package com.google.tests;

import com.codeborne.selenide.Selenide;
import com.google.BaseUiTest;
import org.junit.Test;

public class GoogleSearchTest  extends BaseUiTest {

    @Test
    public void verifyAutocomplete() {
        Selenide.open("/");
        openHomePage().
                typeSearchText("Selenide")
                .verifyFirstValueInAutoComplete("Selenide");
    }


    @Test
    public void verifyResultPage() {
        openHomePage().search("Selenide").verifyFirstResult("Selenide");
    }


}
