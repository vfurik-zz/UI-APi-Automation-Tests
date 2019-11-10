package com.google.utils.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRailReport {

    private static List<Result> results = new ArrayList<>();

    public static void addResult(Result result){
        results.add(result);
    }

    public static void reportResults(){

        String projectId = "1";

        TestRail testRail = TestRail.builder(
                "https://testiqpoc.testrail.io/",
                "yevhen.yeshchev@perfectial.com",
                "vQk7X91BboGr0SKJGjX4").build();

        Project project = testRail.projects().get(Integer.valueOf(projectId)).execute();
        Run run = testRail.runs()
                .add(project.getId(),
                        new Run().setName("TestRail unit test reports ")
                                .setIncludeAll(false)
                                .setCaseIds(results.stream()
                                        .map(k -> k.getCaseId()).collect(Collectors.toList()))
                ).execute();
        List<ResultField> customResultFields = testRail.resultFields().list().execute();
        testRail.results().addForCases(run.getId(), results, customResultFields).execute();
//        testRail.runs().close(run.getId()).execute();
    }
}