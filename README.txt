Appointment Appliction using Java with spring boot and hibernate

This API don't have any GUI as mentioned in the requirement document. So we need to use Postman or any other such tool to run this API
First of all we should have Spring Tool Suite to use this API. 
Open Sprint Tool Suite, CLick on File-> Import-> Existing maven project->Give the path of project and import.This will import the project and add it to the project set.

In src/main/java, there are 4 packages: com.anshul.eastvantage,com.anshul.eastvantage.model,com.anshul.eastvantage.controller,com.anshul.eastvantage.Repository.
To run this project -> open the package -> com.anshu.eastvantage->AppointmentApplication.java file and run it as Spring Boot App.

com.anshul.eastvantage.model-> It have a Appointment.java file defining model(Appointment) along with their properties, their respective getters-setters and toString Method.
com.anshul.eastvantage.Repository-> It have a AppointmentRepository interface extending JpaRepository. Now we can use all methods of hibernate. 
com.anshul.eastvantage.Controller-> It have a AppointmentController class having all the methods and logics of our application.

In src/main/resources -> application.properties file -> All the configurations related to our H2 database is there in that file. Server port is also configured in this file
For our project port number us 6060.


Just launch the application (Run as spring boot app)

Open POSTMAN or any related Application.

1. Creation of an appointment
	In request URL with method = POST-> localhost:6060/appointment.
	In Body give the following json format data :
		{
        "date": "YYYY-MM-DD",
        "startTime": "HH:MM:SS",
        "endTime": "HH:MM:SS",
        "name": "Any string",
        "purpose": "Any String"
    	} 

	The data then went to back-end which validates the field values and then the data will be pushed to Database. It also validate that appointment duration should not conflict with any existing appointment time.
	Duration will be calculated based on start time and end time

2. Display All Records:
	In request URL with method = GET-> localhost:6060/display.

	This will display all the existing Appointment records

3. Display details of any specific Appointment ID:
	In request URL with method = GET-> localhost:6060/display/{id}.     // in Id give the appointment id

	This will display the details of appointment record corresponding to that specific appointment id (IF ANY).

4. Update details of any Record :
	In request URL with method = POST-> localhost:6060/update.
	In Body give the following json format data :
		{
	  "appointmentID": any integer,
        "date": "YYYY-MM-DD",
        "startTime": "HH:MM:SS",
        "endTime": "HH:MM:SS",
        "name": "Any string",
        "purpose": "Any String"
    	} 

	The data then went to back-end which validates the field values and then the data will be updated in Database.

5. Delete an existing record 
	In request URL with method = GET-> localhost:6060/delete/{id}.  		// in Id give the appointment id

	This will delete the appointment record corresponding to that specific appointment id (IF ANY).


6. Display all records in specific date range:
	In request URL with method = GET-> localhost:6060/displayBetweenDate?st=YYYY-MM-DD&ET=YYYY-MM-DD      // in first YYYY-MM-DD-> give start date and second YYYY-MM-DD-> give end date

	This will return all the records in specific date range




Database used is H2.
To access the databse , open browser and hit -> http://localhost:6060/h2-console/

Driver class-> org.h2.Driver
JDBC URL-> jdbc:h2:mem:appointment
User name ->sa
Password -> password
Click on connect 

you can see the database here



Thanks for reading