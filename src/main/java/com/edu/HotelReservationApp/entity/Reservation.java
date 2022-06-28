package com.edu.HotelReservationApp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="reservation_details")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "reservation_details_seq")
	private long resId;
	@Column(nullable=false)
	@NotNull
	@Size(max=3)
	private int noOfGuest;
	private int stayDays;
	private Date checkInDateTime;
	private Date checkOutDateTime;
	
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties("reservation")
	private User user;
	
	@OneToOne
	@JoinColumn(name="roomId")
	@JsonIgnoreProperties("reservation")
	private Room room;
	
	public Reservation(long resId, int noOfGuest, int stayDays, Date checkInDateTime, Date checkOutDateTime, User user,
			Room room) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.user = user;
		this.room = room;
	}
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getResId() {
		return resId;
	}
	public void setResId(long resId) {
		this.resId = resId;
	}
	public int getNoOfGuest() {
		return noOfGuest;
	}
	public void setNoOfGuest(int noOfGuest) {
		this.noOfGuest = noOfGuest;
	}
	public int getStayDays() {
		return stayDays;
	}
	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}
	public Date getCheckInDateTime() {
		return checkInDateTime;
	}
	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}
	public Date getCheckOutDateTime() {
		return checkOutDateTime;
	}
	public void setCheckOutDateTime(Date checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}
	@Override
	public String toString() {
		return "Reservation [resId=" + resId + ", noOfGuest=" + noOfGuest + ", stayDays=" + stayDays
				+ ", checkInDateTime=" + checkInDateTime + ", checkOutDateTime=" + checkOutDateTime + ", user=" + user
				+ ", room=" + room + "]";
	}
	public Reservation(long resId, int noOfGuest, int stayDays, Date checkInDateTime,Date checkOutDateTime) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
	}
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(long resId, int noOfGuest, int stayDays, Date checkInDateTime, Date checkOutDateTime,
			User user) {
		super();
		this.resId = resId;
		this.noOfGuest = noOfGuest;
		this.stayDays = stayDays;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.user = user;
	}
	
}
