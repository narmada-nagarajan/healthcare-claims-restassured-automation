package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CreateObservationTest {
//T13 Create Observation for Patient
    @Test
    public void createObservationForPatient() {

        // Step 1: Create a Patient
        String uniqueId = "OBS-PATIENT-" + System.currentTimeMillis();

        String patientBody = "{"
                + "\"resourceType\": \"Patient\","
                + "\"identifier\": ["
                + "{"
                + "\"system\": \"http://narmada-test.com\","
                + "\"value\": \"" + uniqueId + "\""
                + "}"
                + "],"
                + "\"name\": ["
                + "{"
                + "\"family\": \"ObservationPatient\","
                + "\"given\": [\"Priya\"]"
                + "}"
                + "],"
                + "\"gender\": \"female\""
                + "}";

        Response patientResponse = given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(patientBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Patient");

        patientResponse.then()
                .statusCode(201);

        // Step 2: Extract Patient ID
        String patientId = patientResponse
                .jsonPath()
                .getString("id");

        System.out.println("Created Patient ID: " + patientId);

        // Step 3: Create Observation for that Patient
        String observationBody = "{"
                + "\"resourceType\": \"Observation\","
                + "\"status\": \"final\","
                + "\"code\": {"
                + "\"coding\": ["
                + "{"
                + "\"system\": \"http://loinc.org\","
                + "\"code\": \"8310-5\","
                + "\"display\": \"Body temperature\""
                + "}"
                + "]"
                + "},"
                + "\"subject\": {"
                + "\"reference\": \"Patient/" + patientId + "\""
                + "},"
                + "\"valueQuantity\": {"
                + "\"value\": 98.6,"
                + "\"unit\": \"F\","
                + "\"system\": \"http://unitsofmeasure.org\","
                + "\"code\": \"[degF]\""
                + "}"
                + "}";

        // Step 4: Send Observation request
        given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(observationBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Observation")

        .then()
                .statusCode(201)
                .body("resourceType", equalTo("Observation"))
                .body("id", notNullValue())
                .body("status", equalTo("final"))
                .body("subject.reference",
                        equalTo("Patient/" + patientId))
                .body("valueQuantity.value", equalTo(98.6f));
    }
}