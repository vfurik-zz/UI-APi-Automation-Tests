package com.google.pages;

import com.codeborne.selenide.Condition;
//import io.qameta.allure.Step;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {

    private By textField = By.cssSelector(".gLFyf.gsfi");

   @Step
    public HomePage typeSearchText(String searchText) {
        $(textField).setValue(searchText);
        return this;
    }

    @Step
    public HomePage verifyFirstValueInAutoComplete(String text) {
        $$(".erkvQe li").first().shouldHave(Condition.text(text));
        return this;
    }

    @Step
    public ResultPage search(String searchText) {
        typeSearchText(searchText);
        $(textField).pressEnter();
        return new ResultPage();
    }

}
