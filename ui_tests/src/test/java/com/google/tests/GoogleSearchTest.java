package com.google.tests;

import com.google.BaseUiTest;
import com.google.data_provider.GoogleSearchDataProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GoogleSearchTest extends BaseUiTest {

    @Test
    @Parameters(source = GoogleSearchDataProvider.class)
    public void verifyAutocomplete(String typedValue, String verifyValue) {
        openHomePage().
                typeSearchText(typedValue)
                .verifyFirstValueInAutoComplete(verifyValue);
    }

}
