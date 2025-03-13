package hospital_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	private Connection connection;
	
	
	public Doctor(Connection connection ) {
		this.connection = connection;
		
		
	}

	
	
	public void  viewDoctor() {
		String query = "select * from doctors";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			System.out.println("Patients");
			System.out.println("+------------+------------------------+----------------------+");
			System.out.println("| Doctor ID  | Name                   | Specialization       |");
			System.out.println("+------------+------------------------+----------------------+");
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String specialization = rs.getString("specialization");
//				Helps to format 
				System.out.printf("| %-10s | %-22s | %-20s |\n",id,name,specialization);		
				System.out.println("+------------+------------------------+----------------------+");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean getDoctorById(int id) {
		String query = "select * from doctors where id = ?";
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
