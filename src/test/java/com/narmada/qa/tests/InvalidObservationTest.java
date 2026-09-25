package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import  io.restassured.response.Response;

import org.testng.annotations.Test;

public class InvalidObservationTest {
	
	//TC15 — Invalid Observation: Missing Required status

//Objective:
//Send an Observation request without the mandatory status field and verify that the FHIR server rejects it with a client-error response.

    @Test
    public void createObservationWithoutStatus() {

        // Observation request without the required "status" field
        String observationBody = "{"
                + "\"resourceType\": \"Observation\","
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
                + "\"reference\": \"Patient/999999999\""
                + "},"
                + "\"valueQuantity\": {"
                + "\"value\": 98.6,"
                + "\"unit\": \"F\","
                + "\"system\": \"http://unitsofmeasure.org\","
                + "\"code\": \"[degF]\""
                + "}"
                + "}";

        Response response=  given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(observationBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Observation");
                
                System.out.println("Actual Status Code: " + response.getStatusCode());

        response.then()
                .statusCode(anyOf(
                        equalTo(400),
                        equalTo(422)
                ));
    }
}