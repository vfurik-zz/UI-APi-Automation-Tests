package com.google.utils.testrail;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestRailPublisher extends TestRailAPI {

    public static final int projectID = 1;
    public static final int suiteID = 1;


    private String formatDate(Instant instant){
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .withZone(ZoneId.systemDefault());

        return DATE_TIME_FORMATTER.format(instant);
    }
}
