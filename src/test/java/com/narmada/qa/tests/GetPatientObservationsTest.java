package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetPatientObservationsTest {
	
	
	//TC14 — Get Patient Observations

//Objective:
//Create a Patient → create an Observation for that Patient → search for Observations belonging to that Patient → verify the returned Observations reference the correct Patient.

    @Test
    public void getPatientObservations() {

        // Step 1: Create a Patient
        String uniqueId = "OBS-SEARCH-" + System.currentTimeMillis();

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
                + "\"family\": \"ObservationSearchPatient\","
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

        // Step 3: Create an Observation for this Patient
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

        Response observationResponse = given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(observationBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Observation");

        observationResponse.then()
                .statusCode(201);

        // Step 4: Search Observations for the Patient
        given()
                .header("Accept", "application/fhir+json")
                .queryParam("patient", patientId)

        .when()
                .get("https://hapi.fhir.org/baseR4/Observation")

        .then()
                .statusCode(200)
                .body("resourceType", equalTo("Bundle"))
                .body("entry.resource.resourceType",
                        everyItem(equalTo("Observation")))
                .body("entry.resource.subject.reference",
                        everyItem(equalTo("Patient/" + patientId)));
    }
}