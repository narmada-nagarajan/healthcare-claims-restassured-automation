package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PatientCountSearchTest {

	
	//TC008 Search Patients with _count=2
    @Test
    public void searchPatientsWithCountLimit() {

        given()
            .header("Accept", "application/fhir+json")
            .queryParam("_count", 2)

        .when()
            .get("https://hapi.fhir.org/baseR4/Patient")

        .then()
            .statusCode(200)
            .body("resourceType", equalTo("Bundle"))
            .body("entry.size()", lessThanOrEqualTo(2));
    }
}
