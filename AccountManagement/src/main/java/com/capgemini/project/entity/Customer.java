package com.capgemini.project.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customers")
public class Customer {
	
	
	@Id
	@Column(name="Customer_ID")
	private String customerId; 

	@Column(name="Customer_Address")
	private String address;
	
	@Column(name="Customer_Name")
	private String customerName;
	
	@Column(name="DOB")
	private Date DOB;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="Contact_Number")
	private String contactNumber;
	
	@Column(name="Pan_Number")
	private String panNumber;
	
	@Column(name="Adhaar_Number")
	private String aadharNumber;

	
	
	public String getCustomerId() {
		return customerId;
	}



	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public Date getDOB() {
		return DOB;
	}



	public void setDOB(Date dOB) {
		DOB = dOB;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getContactNumber() {
		return contactNumber;
	}



	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}



	public String getPanNumber() {
		return panNumber;
	}



	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}



	public String getAadharNumber() {
		return aadharNumber;
	}



	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", address=" + address + ", customerName=" + customerName
				+ ", DOB=" + DOB + ", gender=" + gender + ", contactNumber=" + contactNumber + ", panNumber="
				+ panNumber + ", aadharNumber=" + aadharNumber + "]";
	}

	

	public Customer(String customerId, String address, String customerName, Date dOB, String gender,
			String contactNumber, String panNumber, String aadharNumber) {
		super();
		this.customerId = customerId;
		this.address = address;
		this.customerName = customerName;
		DOB = dOB;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
	}



	public Customer() {
		super();
	}

	
	
	
}
