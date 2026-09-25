package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PatientSearchTest {

	//TC006 - Search Patient by Family Name
    @Test
    public void searchPatientByFamilyName() {

        given()
            .header("Accept", "application/fhir+json")
            .queryParam("family", "NarmadaTest")

        .when()
            .get("https://hapi.fhir.org/baseR4/Patient")

        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"))
            .body("entry.resource.resourceType", everyItem(equalTo("Patient")));
    }
}