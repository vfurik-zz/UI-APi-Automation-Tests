package com.test.data.constant;

import com.test.data.Environment;
import com.test.data.PropsController;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

@Log4j
public class BaseEndpoint {

    public final static Environment env;
    public final static String baseApiUrl;
    public final static String baseUIUrl;

    static {
        String systemEnv = System.getProperty("env");
        log.info("SYSTEM ENV IS: " + systemEnv);
        try {
            env = StringUtils.isNotEmpty(systemEnv) ? Environment.valueOf(systemEnv.toUpperCase()) : Environment.DEV;
            log.info("Test environment is: " + env);
        } catch (Exception e) {
            throw new RuntimeException("Unsupported environment detected");
        }
        baseApiUrl = PropsController.props.getApiUrlByEnv();
        log.info("Base API URL is: " + baseApiUrl);

        baseUIUrl = PropsController.props.getUiUrlByEnv();
        log.info("Base UI URL is: " + baseUIUrl);
    }


}
