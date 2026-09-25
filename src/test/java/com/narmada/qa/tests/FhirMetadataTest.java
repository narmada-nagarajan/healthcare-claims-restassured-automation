package com.narmada.qa.tests;
//import com.narmada.qa.config.ApiConfig;
import com.narmada.qa.config.RequestSpecificationProvider;
import com.narmada.qa.config.ResponseSpecificationProvider;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class FhirMetadataTest {

	//TC001 - FHIR server smoke test
	//Business purpose: Before running any health care API tests, verify that the FHIR server is reachable and responding correctly.
    @Test
    public void verifyFhirServerIsAvailable() {

    	given()
        .spec(RequestSpecificationProvider.getRequestSpecification())
    .when()
        .get("/metadata")

        .then()
        .spec(ResponseSpecificationProvider.getResponseSpecification())
        .statusCode(200)
        .body("resourceType", equalTo("CapabilityStatement"));
    }
}