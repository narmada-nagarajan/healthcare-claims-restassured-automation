package com.narmada.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.narmada.qa.config.RequestSpecificationProvider;
import com.narmada.qa.config.ResponseSpecificationProvider;
import com.narmada.qa.pojo.Identifier;
import com.narmada.qa.pojo.PatientName;
import com.narmada.qa.pojo.PatientRequest;

import io.restassured.response.Response;

public class PatientApiTest {

    private String patientId;

    // TC02 - Create Patient
    @Test
    public void createPatient() {

        String uniqueId = "NARMADA-" + System.currentTimeMillis();

        // Create Patient object
        PatientRequest patient = new PatientRequest();

        patient.setResourceType("Patient");
        patient.setGender("female");
        patient.setBirthDate("1992-05-15");

        // Create Identifier object
        Identifier identifier = new Identifier();

        identifier.setSystem("http://narmada-test.com");
        identifier.setValue(uniqueId);

        patient.setIdentifier(Arrays.asList(identifier));

        // Create Patient Name object
        PatientName patientName = new PatientName();

        patientName.setFamily("NarmadaTest");
        patientName.setGiven(Arrays.asList("Priya"));

        patient.setName(Arrays.asList(patientName));

        // Send request
        Response response =
                given()
                    .spec(RequestSpecificationProvider.getRequestSpecification())
                    .header("If-None-Exist",
                            "identifier=http://narmada-test.com|" + uniqueId)
                    .body(patient)

                .when()
                    .post("/Patient");

        // Response validation
        response.then()
                .spec(ResponseSpecificationProvider.getResponseSpecification())
                .statusCode(201)
                .header("Location", notNullValue())
                .body("id", notNullValue());

        // Extract Patient ID
        patientId = response.jsonPath().getString("id");

        System.out.println("Created Patient ID: " + patientId);
    }


    // TC03 - Get Patient by ID
    @Test(dependsOnMethods = "createPatient")
    public void getPatientById() {

        given()
            .spec(RequestSpecificationProvider.getRequestSpecification())

        .when()
            .get("/Patient/" + patientId)

        .then()
            .spec(ResponseSpecificationProvider.getResponseSpecification())
            .statusCode(200)
            .body("resourceType", equalTo("Patient"))
            .body("id", equalTo(patientId))
            .body("gender", equalTo("female"))
            .body("name[0].family", equalTo("NarmadaTest"));
    }
}