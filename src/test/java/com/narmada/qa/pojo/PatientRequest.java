package com.narmada.qa.pojo;

import java.util.List;

public class PatientRequest {

    private String resourceType;
    private List<Identifier> identifier;
    private List<PatientName> name;
    private String gender;
    private String birthDate;
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public List<Identifier> getIdentifier() {
		return identifier;
	}
	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}
	public List<PatientName> getName() {
		return name;
	}
	public void setName(List<PatientName> name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}