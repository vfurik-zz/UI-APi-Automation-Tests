package org.httpbin.models.response_inspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseHeaders {

    @JsonProperty("freeform")
    private String freeform;

    @JsonProperty("Content-Length")
    private String contentLength;

    @JsonProperty("Content-Type")
    private String contentType;

}