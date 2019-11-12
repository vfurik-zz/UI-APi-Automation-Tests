package com.test.data.utils;

import io.qameta.allure.Attachment;

public class AllureUtils {


    @Attachment(value = "Log for test", type = "text/html")
    public static String attachLog(String text) {
        return text;
    }

    @Attachment(value = "Console logs", type = "text/html")
    public static String attachConsoleLog(String text) {
        return text;
    }

    @Attachment(value = "Network log", type = "text/html")
    public static String attachNetworkLog(String text) {
        return text;
    }


}
