
package hospital_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String username = "root";
	private static final String password = "root";
	public static void main(String[] args) {
		Scanner scanner = new  Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			Patient patient = new Patient(connection, scanner);
			Doctor doctor = new Doctor(connection);
			Appointment appointment = new Appointment(patient, doctor, connection, scanner);
			while (true) {
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1. Add Patients");
				System.out.println("2. View Patients");
				System.out.println("3. View Doctor");
				System.out.println("4. Book Appointment");
				System.out.println("5. Exit");
				System.out.println("Enter your choice ");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1: 
					patient.addPatient();
					System.out.println();
					break;
				case 2: 
					patient.viewPatient();
					System.out.println();
					break;
				case 3: 
					doctor.viewDoctor();
					System.out.println();
					break;
				case 4: 
					appointment.bookAppointment();
					System.out.println();
					break;
				case 5:
					System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM!!!S");
					return;
					
				
				default : 
					System.out.println("Enter a valid choice!!!");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
