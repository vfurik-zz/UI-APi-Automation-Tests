package com.test.data.props;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllProps {

    @JsonProperty("environment")
    private List<EnvironmentItem> environment;

    @JsonProperty("selenoid_url")
    private String selenoidUrl;

}