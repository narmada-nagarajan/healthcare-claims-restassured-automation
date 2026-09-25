package com.narmada.qa.config;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationProvider {

    public static ResponseSpecification getResponseSpecification() {

        return new ResponseSpecBuilder()
                .expectContentType("application/fhir+json")
                .build();
    }
}