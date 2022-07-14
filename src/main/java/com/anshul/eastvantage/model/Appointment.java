package com.anshul.eastvantage.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Class for defining model(Appointment) along with their properties, their respective getters-setters and toString Method.

@Table(name = "Appointment")
@Entity

public class Appointment {

	@Id
	@Column(name = "appointmentID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentID;

	@Column(name = "date")
	private Date date;

	@Column(name = "startTime")
	private Time startTime;

	@Column(name = "endTime")
	private Time endTime;

	@Column(name = "name")
	private String name;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "duration")
	private Time duration;

	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentID=" + appointmentID + ", date=" + date + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", name=" + name + ", purpose=" + purpose + ", duration=" + duration + "]";
	}

}
