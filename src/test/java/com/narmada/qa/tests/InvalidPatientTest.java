package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class InvalidPatientTest {
	
	//TC05 — Invalid Patient resource type — 400
	
    @Test
    public void createPatientWithInvalidResourceType() {

        String requestBody = "{"
                + "\"resourceType\": \"InvalidPatient\","
                + "\"name\": ["
                + "{"
                + "\"family\": \"NarmadaTest\","
                + "\"given\": [\"Priya\"]"
                + "}"
                + "],"
                + "\"gender\": \"female\""
                + "}";

        given()
            .header("Content-Type", "application/fhir+json")
            .header("Accept", "application/fhir+json")
            .body(requestBody)

        .when()
            .post("https://hapi.fhir.org/baseR4/Patient")

        .then()
            .statusCode(400);
    }
}