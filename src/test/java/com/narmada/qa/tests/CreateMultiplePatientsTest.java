package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateMultiplePatientsTest {

    @DataProvider(name = "patientData")
    public Object[][] patientData() {

        return new Object[][] {
                {"NarmadaOne", "Priya", "female"},
                {"NarmadaTwo", "Anitha", "female"},
                {"NarmadaThree", "Kumar", "male"}
        };
    }
//Create Multiple Patients using DataProvider
    @Test(dataProvider = "patientData")
    public void createPatient(String familyName, String givenName, String gender) {

        String uniqueId = "DATA-" + System.currentTimeMillis();

        String requestBody = "{"
                + "\"resourceType\": \"Patient\","
                + "\"identifier\": ["
                + "{"
                + "\"system\": \"http://narmada-test.com\","
                + "\"value\": \"" + uniqueId + "\""
                + "}"
                + "],"
                + "\"name\": ["
                + "{"
                + "\"family\": \"" + familyName + "\","
                + "\"given\": [\"" + givenName + "\"]"
                + "}"
                + "],"
                + "\"gender\": \"" + gender + "\""
                + "}";

        given()
                .header("Content-Type", "application/fhir+json")
                .header("Accept", "application/fhir+json")
                .body(requestBody)

        .when()
                .post("https://hapi.fhir.org/baseR4/Patient")

        .then()
                .statusCode(201)
                .body("resourceType", equalTo("Patient"))
                .body("id", notNullValue());

        System.out.println(
                "Patient created: "
                + familyName + " "
                + givenName
                + " | Gender: " + gender
        );
    }
}