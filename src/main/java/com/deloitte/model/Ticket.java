package com.deloitte.model;

import java.util.*;

public class Ticket {

	private long pnrNo;
	private String passengerName;
	private String gender;
	private int age;
	private Date journeyDate;
	private String seatNo;
	private long bookingId;
	private String status;

	// Getters and Setters

	public long getPnrNo() {

		return pnrNo;

	}

	public void setPnrNo(long pnrNo) {

		this.pnrNo = pnrNo;

	}

	public String getPassengerName() {

		return passengerName;

	}

	public void setPassengerName(String passengerName) {

		this.passengerName = passengerName;

	}

	public String getGender() {

		return gender;

	}

	public void setGender(String gender) {

		this.gender = gender;

	}

	public int getAge() {

		return age;

	}

	public void setAge(int age) {

		this.age = age;

	}

	public Date getJourneyDate() {

		return journeyDate;

	}

	public void setJourneyDate(Date journeyDate) {

		this.journeyDate = journeyDate;

	}

	public String getSeatNo() {

		return seatNo;

	}

	public void setSeatNo(String seatNo) {

		this.seatNo = seatNo;

	}

	public long getBookingId() {

		return bookingId;

	}

	public void setBookingId(long bookingId) {

		this.bookingId = bookingId;

	}

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}
}
