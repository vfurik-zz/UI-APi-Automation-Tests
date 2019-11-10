package com.google.tests;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Case;
import com.codepine.api.testrail.model.Run;
import com.google.utils.annotations.CaseID;
import com.google.utils.testrail.TestRailAPI;
import com.google.utils.testrail.TestRailTestRunWatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(TestRailTestRunWatcher.class)
public class TestRailTesting {

    public static final int projectID = 1;
    public static final int suiteID = 1;

    private String formatDate(Instant instant){
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                .withZone(ZoneId.systemDefault());

        return DATE_TIME_FORMATTER.format(instant);
    }

    @Test
    @CaseID(id = 1)
    void testCase1() {
//        TestRailAPI testRail = new TestRailAPI();
//
//        List<Case> cases = testRail.getAllCases(projectID, suiteID);
//
//        List<Integer> caseIDs = cases.stream().map(Case::getId).collect(Collectors.toList());
//
//        Run run = testRail.createTestRun(projectID, "Automated Run " + formatDate(Instant.now()))
//                .setSuiteId(suiteID)
//                .setIncludeAll(false)
//                .setCaseIds(caseIDs);
        System.out.println("test");
    }

    @Test
    @CaseID(id = 2)
    void testCase2() {
        System.out.println("test");
    }

}
