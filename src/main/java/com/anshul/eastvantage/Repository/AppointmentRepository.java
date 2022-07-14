package com.anshul.eastvantage.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anshul.eastvantage.model.Appointment;

//Repository to extends JPA Repository so that we can use all pre defined methods of hibernate on Appointment model

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	// Method to fetch all appointment details in given date range

	@Query("FROM Appointment WHERE date>=?1 and date<=?2")
	List<Appointment> displayBetweenDate(Date std, Date etd);

}
