package com.google.data_provider;

public class GoogleSearchDataProvider {

    public static Object[] provideData() {
        return new Object[]{
                new Object[]{"JUnit", "Junit"},
                new Object[]{"TestNG", "TestNG_test"} // test should fails
        };
    }
}
