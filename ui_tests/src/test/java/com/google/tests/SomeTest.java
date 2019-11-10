package com.google.tests;

import com.google.BaseUiTest;
import com.google.utils.annotations.CaseID;
import com.google.utils.testrail.TestRailTestRunWatcher;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.$$;

@Log4j
public class SomeTest extends BaseUiTest {

    @BeforeEach
    void beforeEach() {}

    @AfterEach
    void afterEach() {

    }

    @Tags(value = {@Tag("regression")})
    @Test
    @CaseID(id = 1)
    void testGoogleSearch() {
        log.info("In test");
        openHomePage()
                .search("Selenide")
                .verifyFirstResult("sadasd_FAIL");
    }

    @Test
    @CaseID(id = 2)
    void testCase() {
        System.out.println("test");
    }
}
