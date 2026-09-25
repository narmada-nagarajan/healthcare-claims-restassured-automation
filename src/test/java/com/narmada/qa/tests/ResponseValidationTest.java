package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class ResponseValidationTest {

	//TC09 — Validate Response Headers + Response Time
    @Test
    public void validateResponseHeadersAndResponseTime() {

        given()
            .header("Accept", "application/fhir+json")

        .when()
            .get("https://hapi.fhir.org/baseR4/metadata")

        .then()
            .statusCode(200)
            .header("Content-Type", containsString("application/fhir+json"))
            .time(lessThan(5000L));
    }
}