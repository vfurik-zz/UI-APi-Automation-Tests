package org.httpbin.models.request_inspection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IpAddressResponse {

    @JsonProperty("origin")
    private String origin;

}