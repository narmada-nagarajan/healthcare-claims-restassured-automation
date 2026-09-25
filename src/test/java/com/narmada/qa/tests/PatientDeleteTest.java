package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PatientDeleteTest {
//TC11 — Delete Patient
    @Test
    public void deletePatient() {

        // Step 1: Create a Patient
        String uniqueId = "DELETE-" + System.currentTimeMillis();

        String requestBody = "{"
                + "\"resourceType\": \"Patient\","
                + "\"identifier\": ["
                + "{"
                + "\"system\": \"http://narmada-test.com\","
                + "\"value\": \"" + uniqueId + "\""
                + "}"
                + "],"
                + "\"name\": ["
                + "{"
                + "\"family\": \"NarmadaDeleteTest\","
                + "\"given\": [\"Priya\"]"
                + "}"
                + "],"
                + "\"gender\": \"female\""
                + "}";

        Response createResponse = given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(requestBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Patient");

        createResponse.then()
                .statusCode(201);

        // Step 2: Extract Patient ID
        String patientId = createResponse.jsonPath().getString("id");

        System.out.println("Patient created for deletion: " + patientId);

        // Step 3: Delete the Patient
        given()
                .header("Accept", "application/fhir+json")

        .when()
                .delete("https://hapi.fhir.org/baseR4/Patient/" + patientId)

        .then()
                .statusCode(200);
    }
}