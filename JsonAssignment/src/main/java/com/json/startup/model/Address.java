package com.json.startup.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Address {
	
	@JsonProperty("streetAddress")
	String streetAddress;
	
	public Address() {
		super();
	}

	public Address(String streetAddress) {
		super();
		this.streetAddress = streetAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	@Override
	public String toString() {
		return "Address [streetAddress=" + streetAddress + "]";
	
	}
   
}
