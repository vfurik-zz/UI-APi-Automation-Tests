package com.test.data.props;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatabaseCred {

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

}