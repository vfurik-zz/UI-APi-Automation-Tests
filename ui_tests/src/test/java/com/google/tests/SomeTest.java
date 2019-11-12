package com.google.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.google.BaseUiTest;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

@Log4j
public class SomeTest extends BaseUiTest {

    @Tags(value = {@Tag("regression11")})
    @Test
    void test11() {
        log.info("In test");
        openHomePage();
        $(".iblpc").shouldBe(Condition.visible);
        $("[class='gLFyf gsfi']").setValue("qwerty").pressEnter();
        Selenide.sleep(5000);

    }

}
