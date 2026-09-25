package com.narmada.qa.pojo;

import java.util.List;

public class PatientName {

    private String family;
    private List<String> given;
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public List<String> getGiven() {
		return given;
	}
	public void setGiven(List<String> given) {
		this.given = given;
	}

}