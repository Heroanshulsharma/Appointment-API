package com.anshul.eastvantage.Controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.eastvantage.Repository.AppointmentRepository;
import org.springframework.web.bind.annotation.RequestBody;
import com.anshul.eastvantage.model.Appointment;

// Controller class for Appointment API

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	// Method to register a new Appointment

	@PostMapping("/appointment")
	public String save(@RequestBody Appointment appointment) {

		List<Appointment> allAppointments = appointmentRepository.findAll();

		// Validation of time range
		if (appointment.getStartTime().getTime() >= appointment.getEndTime().getTime()) {
			return "Start time should be less than End time ";
		}

		// Validating that there are no scheduled appointments in the given appointment
		// duration

		for (Appointment temp : allAppointments) {
			if (temp.getDate().toString().equals(appointment.getDate().toString())) {
				if (temp.getStartTime().getTime() >= appointment.getStartTime().getTime()
						&& temp.getStartTime().getTime() < appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();

				} else if (temp.getEndTime().getTime() > appointment.getStartTime().getTime()
						&& temp.getEndTime().getTime() <= appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();
				} else if (temp.getStartTime().getTime() <= appointment.getStartTime().getTime()
						&& temp.getEndTime().getTime() >= appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();

				}

			}
		}

		// Logic to compute duration using start and end time

		long differenceInMilliSeconds = Math
				.abs(appointment.getEndTime().getTime() - appointment.getStartTime().getTime());

		// Calculating the difference in Hours
		long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;

		// Calculating the difference in Minutes
		long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;

		// Calculating the difference in Seconds
		long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;

		Time duration = Time.valueOf(differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds);
		appointment.setDuration(duration);

		appointmentRepository.save(appointment);
		return "You record entered successfully. Your appointment ID is " + appointment.getAppointmentID();

	}

	// Method to display all existing appointment records

	@GetMapping("/display")

	public List<Appointment> display() {
		List<Appointment> data = appointmentRepository.findAll();
		return data;
	}

	// Method to display appointment details for a specific appointment id

	@GetMapping("/display/{id}")
	public String get(@PathVariable int id) {
		Appointment appointmentObj = appointmentRepository.getOne(id);
		if (appointmentObj == null) {
			return "No record exist with appointment ID: " + id;
		}
		return appointmentObj.toString();
	}

	// Method to update details of some existing appointment records

	@PostMapping("/update")
	public String update(@RequestBody Appointment appointment) {

		Appointment appointmentObj = appointmentRepository.getOne(appointment.getAppointmentID());
		if (appointmentObj == null) {
			return "No record exist with appointment ID: " + appointment.getAppointmentID();
		}
		List<Appointment> allAppointments = appointmentRepository.findAll();

		// Validation of time range
		if (appointment.getStartTime().getTime() >= appointment.getEndTime().getTime()) {
			return "Start time should be less than End time ";
		}

		// Validating that there are no scheduled appointments in the given appointment
		// duration

		for (Appointment temp : allAppointments) {
			if (temp.getDate().toString().equals(appointment.getDate().toString())) {
				if (temp.getStartTime().getTime() >= appointment.getStartTime().getTime()
						&& temp.getStartTime().getTime() < appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();

				} else if (temp.getEndTime().getTime() > appointment.getStartTime().getTime()
						&& temp.getEndTime().getTime() <= appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();
				} else if (temp.getStartTime().getTime() <= appointment.getStartTime().getTime()
						&& temp.getEndTime().getTime() >= appointment.getEndTime().getTime()) {
					return "Conflict in your appointment. Another appointment is already scheduled for this date and time having appointment ID: "
							+ temp.getAppointmentID();

				}

			}
		}

		// Logic to compute duration using start and end time

		long differenceInMilliSeconds = Math
				.abs(appointment.getEndTime().getTime() - appointment.getStartTime().getTime());

		// Calculating the difference in Hours
		long differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;

		// Calculating the difference in Minutes
		long differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;

		// Calculating the difference in Seconds
		long differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;

		Time duration = Time.valueOf(differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds);
		appointment.setDuration(duration);

		appointmentRepository.save(appointment);

		return "Record updated for Appointment ID: " + appointment.getAppointmentID();
	}

	// Method to delete an existing Appointment record

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		Appointment appointmentObj = appointmentRepository.getOne(id);
		if (appointmentObj == null) {
			return "No record exist with appointment ID: " + id;
		}
		appointmentRepository.deleteById(id);
		return "Appointment has been deleted with id:" + id;
	}

	// Method to display all existing records in specific date range

	@GetMapping("/displayBetweenDate")
	public List<Appointment> displayBetweenDate(HttpServletRequest req) {

		String st = req.getParameter("st");
		Date std = Date.valueOf(st);
		String et = req.getParameter("et");
		Date etd = Date.valueOf(et);
		List<Appointment> data = appointmentRepository.displayBetweenDate(std, etd);
		return data;
	}

}
