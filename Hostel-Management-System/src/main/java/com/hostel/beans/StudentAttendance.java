package com.hostel.beans;

import org.springframework.stereotype.Component;

@Component
public class StudentAttendance {

	private String id;
	private String name;
	private String mobileNumber;
	private String date;
	private String status;
	private String attenDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAttenDate() {
		return attenDate;
	}
	public void setAttenDate(String attenDate) {
		this.attenDate = attenDate;
	}
	
	@Override
	public String toString() {
		return "StudentAttendance [id=" + id + ", name=" + name + ", mobileNumber=" + mobileNumber + ", date=" + date
				+ ", status=" + status + ", attenDate=" + attenDate + "]";
	}
}
