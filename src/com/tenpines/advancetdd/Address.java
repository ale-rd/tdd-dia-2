package com.tenpines.advancetdd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="ADDRESSES")
public class Address { 
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String streetName;
	@Min(1)
	private int streetNumber;
	@NotEmpty
	private String town;
	@Min(1000)
	private int zipCode;
	@NotEmpty
	private String province;
	
	public Address()
	{}
	
	public Address(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public int getStreetNumber() {
		return streetNumber;
	}
	
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
}
