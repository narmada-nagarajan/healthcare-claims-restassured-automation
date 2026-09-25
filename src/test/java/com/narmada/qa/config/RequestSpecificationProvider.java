package com.narmada.qa.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationProvider {

    public static RequestSpecification getRequestSpecification() {

        return new RequestSpecBuilder()
                .setBaseUri(ApiConfig.BASE_URI)
                .setAccept("application/fhir+json")
                .build();
    }
}