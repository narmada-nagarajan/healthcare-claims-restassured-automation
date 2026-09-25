package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PatientUpdateTest {
	
	//

    @Test
    public void updatePatient() {

        // Step 1: Create a Patient
        String uniqueId = "UPDATE-" + System.currentTimeMillis();

        String createBody = "{"
                + "\"resourceType\": \"Patient\","
                + "\"identifier\": ["
                + "{"
                + "\"system\": \"http://narmada-test.com\","
                + "\"value\": \"" + uniqueId + "\""
                + "}"
                + "],"
                + "\"name\": ["
                + "{"
                + "\"family\": \"NarmadaBeforeUpdate\","
                + "\"given\": [\"Priya\"]"
                + "}"
                + "],"
                + "\"gender\": \"female\""
                + "}";

        Response createResponse = given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(createBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Patient");

        createResponse.then()
                .statusCode(201);

        // Step 2: Extract the generated Patient ID
        String patientId = createResponse.jsonPath().getString("id");

        System.out.println("Patient created for update: " + patientId);

        // Step 3: Prepare updated Patient
        String updateBody = "{"
                + "\"resourceType\": \"Patient\","
                + "\"id\": \"" + patientId + "\","
                + "\"name\": ["
                + "{"
                + "\"family\": \"NarmadaUpdated\","
                + "\"given\": [\"Priya\"]"
                + "}"
                + "],"
                + "\"gender\": \"female\""
                + "}";

        // Step 4: Update the Patient
        given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(updateBody)

        .when()
                .put("https://hapi.fhir.org/baseR4/Patient/" + patientId)

        .then()
                .statusCode(200)
                .body("resourceType", equalTo("Patient"))
                .body("id", equalTo(patientId))
                .body("name[0].family", equalTo("NarmadaUpdated"))
                .body("meta.versionId", notNullValue());
    }
}