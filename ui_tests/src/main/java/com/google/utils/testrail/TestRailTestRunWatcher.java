package com.google.utils.testrail;

import com.codepine.api.testrail.TestRail;
import com.google.utils.annotations.CaseID;
import com.codepine.api.testrail.model.Result;
import java.util.Optional;

import com.google.utils.enums.TestRailRunStatus;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.junit.jupiter.api.extension.TestWatcher;


public class TestRailTestRunWatcher implements TestWatcher, BeforeAllCallback {
    private static boolean started = false;
    private static final String TESTRAIL_REPORT = "TEST_RAIL";

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        addResult(extensionContext, TestRailRunStatus.UNTESTED);
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        addResult(extensionContext,TestRailRunStatus.PASSED);
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        addResult(extensionContext,TestRailRunStatus.RETEST);
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        addResult(extensionContext, TestRailRunStatus.FAILED);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if(!started) {
            getStore(extensionContext).put(TESTRAIL_REPORT, new CloseableOnlyOnceResource());
            started = true;
        }

    }

    private static class CloseableOnlyOnceResource implements
            ExtensionContext.Store.CloseableResource {
        @Override
        public void close() {
            //After all tests run hook.
            //Any additional desired action goes here
            TestRailReport.reportResults();
        }
    }

    private void addResult(ExtensionContext extensionContext, TestRailRunStatus status){

        if(extensionContext.getElement().isPresent() && extensionContext.getElement().get().isAnnotationPresent(CaseID.class)){
            CaseID element = extensionContext.getElement().get().getAnnotation(CaseID.class);
            Result result = new Result()
                    .setTestId(Integer.valueOf(element.id()))
                    .setStatusId(status.getId())
                    .setCaseId(Integer.valueOf(element.id()));

            if (extensionContext.getExecutionException().isPresent()) {
                result.setComment(extensionContext.getExecutionException().get().getMessage());
            }
            TestRailReport.addResult(result);
        }
    }

    private Store getStore(ExtensionContext context) {
        return context.getRoot().getStore(Namespace.GLOBAL);
    }
}