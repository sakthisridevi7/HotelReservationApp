package com.edu.HotelReservationApp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_details")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_details_seq")
	private long userId;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String username;
	private String password;
	private String emailId;
	private long aadharNumber;
	private String fullAddress;
	
	@OneToMany(mappedBy="user")
	@JsonIgnoreProperties("user")
	private List<Reservation> reservation;
	
	public User(long userId, String firstName, String lastName, String contactNo, String username, String password,
			String emailId, long aadharNumber, String fullAddress, List<Reservation> reservation) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.aadharNumber = aadharNumber;
		this.fullAddress = fullAddress;
		this.reservation = reservation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Reservation> getReservation() {
		return reservation;
	}
	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNo="
				+ contactNo + ", username=" + username + ", password=" + password + ", emailId=" + emailId
				+ ", aadharNumber=" + aadharNumber + ", fullAddress=" + fullAddress + ", reservation=" + reservation
				+ "]";
	}
	public User(long userId, String firstName, String lastName, String contactNo, String username, String password,
			String emailId, long aadharNumber, String fullAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.aadharNumber = aadharNumber;
		this.fullAddress = fullAddress;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}