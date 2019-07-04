package com.google.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class ResultPage {

    @Step
    public ResultPage verifyFirstResult(String text) {
        $$(".srg h3").first().shouldHave(Condition.text(text));
        return this;
    }
}
