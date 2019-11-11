package com.google.utils.enums;

public enum TestRailRunStatus {
    PASSED(1),
    BLOCKED(2),
    UNTESTED(3),
    RETEST(4),
    FAILED(5);

    private int id;
    public int getId() {
        return id;
    }

    TestRailRunStatus(int id) {
        this.id = id;
    }
}