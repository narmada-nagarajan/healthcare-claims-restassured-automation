package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class NonExistentPatientTest {

	
	//TC004 Non-existent Patient — 404
    @Test
    public void getNonExistentPatient() {

        given()
            .header("Accept", "application/fhir+json")

        .when()
            .get("https://hapi.fhir.org/baseR4/Patient/999999999")

        .then()
            .statusCode(404)
            .body("resourceType", equalTo("OperationOutcome"));
    }
}