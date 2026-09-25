package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PatientNoMatchSearchTest {

	//TC007-Search Patient with No Matching Result
    @Test
    public void searchPatientWithNoMatch() {

        given()
            .header("Accept", "application/fhir+json")
            .queryParam("family", "XYZ_NoSuchPatient_99999")

        .when()
            .get("https://hapi.fhir.org/baseR4/Patient")

        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"))
            .body("total", equalTo(0));
    }
}