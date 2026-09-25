# Healthcare Claims REST Assured API Automation

A self-directed API automation project built using **Java, REST Assured, and TestNG** to validate healthcare-related FHIR APIs and demonstrate a maintainable API automation framework.

## 📌 Project Overview

This project automates healthcare API scenarios covering patient management, observations, search operations, response validation, negative scenarios, and patient lifecycle operations.

The automation suite contains **18 test scenarios** designed to validate both functional and negative API behavior.

### Key Areas Covered

* Patient creation
* Patient retrieval
* Patient update
* Patient deletion
* Patient search
* No-match search
* Patient count/search validation
* Patient observations
* Observation creation
* Invalid patient requests
* Invalid observation requests
* Non-existent patient validation
* Deleted patient verification
* FHIR metadata validation
* Response structure and field validation

## 🛠️ Technology Stack

| Technology      | Purpose                         |
| --------------- | ------------------------------- |
| Java            | Programming language            |
| REST Assured    | REST API automation             |
| TestNG          | Test execution and assertions   |
| Maven           | Dependency and build management |
| POJO            | Request payload modelling       |
| JSON / JSONPath | Request and response validation |
| Git             | Version control                 |
| GitHub          | Source-code repository          |

## 🏗️ Framework Structure

```text
healthcare-restassured-api
│
├── pom.xml
├── .gitignore
│
└── src
    └── test
        └── java
            └── com.narmada.qa
                │
                ├── config
                │   ├── ApiConfig.java
                │   ├── RequestSpecificationProvider.java
                │   └── ResponseSpecificationProvider.java
                │
                ├── pojo
                │   ├── Identifier.java
                │   ├── PatientName.java
                │   └── PatientRequest.java
                │
                └── tests
                    ├── CreateMultiplePatientsTest.java
                    ├── CreateObservationTest.java
                    ├── FhirMetadataTest.java
                    ├── GetPatientObservationsTest.java
                    ├── InvalidObservationTest.java
                    ├── InvalidPatientTest.java
                    ├── NonExistentPatientTest.java
                    ├── PatientApiTest.java
                    ├── PatientCountSearchTest.java
                    ├── PatientDeleteTest.java
                    ├── PatientNoMatchSearchTest.java
                    ├── PatientSearchTest.java
                    ├── PatientUpdateTest.java
                    ├── ResponseValidationTest.java
```
