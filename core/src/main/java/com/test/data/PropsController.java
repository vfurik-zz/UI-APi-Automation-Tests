package com.test.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.data.constant.BaseEndpoint;
import com.test.data.props.AllProps;
import com.test.data.props.DatabaseCred;
import com.test.data.props.EnvironmentItem;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class PropsController {

    private PropsController() {
        loadPropertiesFromFile();
    }

    public final static PropsController props = new PropsController();

    private AllProps allProps;

    private void loadPropertiesFromFile() {
        try {
            this.allProps = new ObjectMapper().readValue(this.getClass().getClassLoader().getResourceAsStream("allProps.json"), AllProps.class);
        } catch (IOException e) {
            log.error("Error during getting reading properties from file allProps.json", e);
        }
    }

    public DatabaseCred getDatabaseCredByEnv() {
        return filterContentByEnv(BaseEndpoint.env).getDatabase();
    }

    public String getSelenoidUrl(){
        return this.allProps.getSelenoidUrl();
    }

    public String getApiUrlByEnv() {
        return filterContentByEnv(BaseEndpoint.env).getApiUrl();
    }

    public String getUiUrlByEnv() {
        return filterContentByEnv(BaseEndpoint.env).getUiUrl();
    }

    private EnvironmentItem filterContentByEnv(Environment environment) {
        List<EnvironmentItem> collect = props.allProps.getEnvironment().stream().filter(e -> e.getName().toUpperCase().equals(environment.name())).collect(Collectors.toList());
        if (collect.size() != 1) {
            log.error("Unsupported environment exception");
            throw new RuntimeException("Unsupported environment exception");
        }
        return collect.get(0);
    }

}
