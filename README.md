# General information
Example of implementing UI and API tests frameworks

# Frameworks
- [Rest-Assured](http://rest-assured.io/)
- [Allure](https://docs.qameta.io/allure/)
- [AssertJ](https://github.com/joel-costigliola/assertj-core/)
- [JUnit4](https://junit.org/junit4/)
- [Selenide](https://selenide.org/)
- [JUnitParams](https://github.com/Pragmatists/JUnitParams)
- [Lombok](https://projectlombok.org/)

# Launching tests, generating report
Available environments: dev, qa, stage
 Use -Denv=[stage|qa|dev] for running tests on different environments
 
For running UI tests on stage:
```bash
    ./gradlew clean :ui_tests:allTests -Denv=stage
 ```

For running API tests on dev:
```bash
    ./gradlew clean :api_tests:allTests -Denv=dev
 ```
 
 For downloading allure plugin(run only one time):
 ```bash
     ./gradlew downloadAllure
  ```
 
 For generating report for UI tests:
 ```bash
     ./gradlew :ui_tests:allureServe
  ```
  
 For generating report for API tests:
 ```bash
     ./gradlew :api_tests:allureServe
  ```
