package com.test.data.props;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
public class EnvironmentItem{

	@JsonProperty("database")
	private DatabaseCred database;

	@JsonProperty("api_url")
	private String apiUrl;

	@JsonProperty("name")
	private String name;

	@JsonProperty("ui_url")
	private String uiUrl;

}