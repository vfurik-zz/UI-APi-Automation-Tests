package com.google.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.google.BaseUiTest;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.Selenide.$$;

@Log4j
public class SomeTest extends BaseUiTest {

    @BeforeEach
    void beforeEach() {

    }

    @Tags(value = {@Tag("regression")})
    @Test
    void test1() {
        log.info("In test");
        openHomePage();
        $$(".sda").shouldHaveSize(2);

    }

}
