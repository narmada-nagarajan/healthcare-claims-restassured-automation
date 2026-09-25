package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class VerifyDeletedPatientTest {

	//TC12 — Verify Deleted Patient
	//Create a Patient → delete it → try to GET the same Patient → verify the server returns 410 Gone.
    @Test
    public void verifyDeletedPatient() {

        // Step 1: Create a Patient
        String uniqueId = "VERIFY-DELETE-" + System.currentTimeMillis();

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
                + "\"family\": \"NarmadaDeleteVerify\","
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

        System.out.println("Patient created: " + patientId);

        // Step 3: Delete the Patient
        given()
                .header("Accept", "application/fhir+json")

        .when()
                .delete("https://hapi.fhir.org/baseR4/Patient/" + patientId)

        .then()
                .statusCode(200);

        // Step 4: Try to retrieve the deleted Patient
        given()
                .header("Accept", "application/fhir+json")

        .when()
                .get("https://hapi.fhir.org/baseR4/Patient/" + patientId)

        .then()
                .statusCode(410);
    }
}