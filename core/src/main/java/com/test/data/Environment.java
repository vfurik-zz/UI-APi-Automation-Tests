package com.test.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Environment {
    @JsonProperty("QA")
    QA,
    @JsonProperty("DEV")
    DEV,
    @JsonProperty("stage")
    STAGE
}
