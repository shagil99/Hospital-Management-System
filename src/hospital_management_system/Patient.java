package hospital_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	
	private Connection connection;
	private Scanner scanner;
	
	public Patient(Connection connection , Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
		
	}

	
	public void addPatient() {
		System.out.print("Enter Patient name: ");
		String name = scanner.next();
		
		System.out.print("Enter Patient's age: ");
		int age = scanner.nextInt();
		System.out.print("Enter Patients Gender: " );
		String gender = scanner.next();
		try {
			String query = "insert into patients(name, age, gender) values (?,?,?) ";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, age);
			preparedStatement.setString(3, gender);
			int affectedrows = preparedStatement.executeUpdate();
			if(affectedrows>0) {
				System.out.println("Patient added Succesfully");
			}
			else {
				System.out.println("Failed to add Patient");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void  viewPatient() {
		String query = "select * from patients";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			System.out.println("Patients");
			System.out.println("+------------+------------------------+---------+-------------+");
			System.out.println("| Patient ID | Name                   | Age     | Gender      |");
			System.out.println("+------------+------------------------+---------+-------------+");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
//				Helps to format 
				System.out.printf("| %-10s | %-22s | %-7s | %-11s |\n" , id,name,age,gender);		
				System.out.println("+------------+------------------------+---------+-------------+");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean getPatientById(int id) {
		String query = "select * from patients where id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				return true;
			}
			else { 
				return false;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
