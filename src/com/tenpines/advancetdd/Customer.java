package com.tenpines.advancetdd;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table( name = "CUSTOMERS" )
public class Customer {

	@Id
	@GeneratedValue
	private long id; 
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Pattern(regexp="D|C")
	private String identificationType;
	@NotEmpty
	private String identificationNumber;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Address> addresses;
	
	public Customer()
	{
		addresses = new HashSet<Address>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(String identificationType) {
		this.identificationType = identificationType;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public void addAddress(Address anAddress){
		addresses.add(anAddress);
	}

	public static void main(String[] args){
		try {
			new CustomerImportTest().importCustomers(new FileReader("resources/input.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
