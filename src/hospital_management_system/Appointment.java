package hospital_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
	private Connection connection;
	private Scanner scanner;
	private Patient patient;
	private Doctor doctor;
	
	public Appointment (Patient patient, Doctor doctor, Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner=scanner;
		this.patient=patient;
		this.doctor=doctor;
	}
	public  void bookAppointment() {
		System.out.print("Enter patient id: ");
		int patientId = scanner.nextInt();
		System.out.print("Enter doctor id: ");
		int doctorId = scanner.nextInt();
		System.out.print("Enter  Appointment date (YYYY-MM-DD): ");
		String appointmentDate = scanner.next();
		if(patient.getPatientById(patientId)&&doctor.getDoctorById(doctorId)) {
			if(checkDoctorAvailability(doctorId,appointmentDate)) {
				String query = "insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
				try {
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setInt(1, patientId);
					statement.setInt(2, doctorId);
					statement.setString(3, appointmentDate);
					int rs = statement.executeUpdate();
					if(rs>0) {
						System.out.println("Appointment Booked Successfully");
					}
					else {
						System.out.println("Failed to Book Appointment");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else {
			System.out.println("Doctor not available on this  date ");	
			}
		}else
		{
			System.out.println("Either doctor or patient doesn't exist");
		}
	}
	
	public boolean checkDoctorAvailability(int doctorId , String appointmentDate  ) {
//		count(*) tells about the number  of row are corresponding to this query if it return 1 then we will state state that doctor is not available for the date 
		String query = "select count(*) from appointments where doctor_id = ? and appointment_date = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, doctorId);
			statement.setString(2, appointmentDate);
			ResultSet resultset = statement.executeQuery();
			if(resultset.next()) {
				int count = resultset.getInt(1);
				if(count==0) {
					return true;
				}else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	return false;
	
	}

}
