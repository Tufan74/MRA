package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datatypes.TimeData;
import interfaces.IMovieDatabase;

public class MD_Adapter implements IMovieDatabase {
	private static MD_Adapter instance;
	
	/**
	 * Constructor which loads the corresponding driver for the chosen database type
	 */
	private MD_Adapter() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static MD_Adapter getInstance() {
		if (instance == null) {
			instance = new MD_Adapter();
		}

		return instance;
	}

	public static void setInstance(MD_Adapter dbfacade) {
		instance = dbfacade;
	}
	
	
	
	public boolean rate(int rating, String uid, String mid, String comment) {
		 
		String sqlInsert = "INSERT INTO mydb01.rate (rating,uid,mid,comment) VALUES (?,?,?,?)";
        
		Boolean valid=check_rate(rating,uid,mid,comment);
		if(valid)
		{
		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/mydb01")) {
            
			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setInt(1, rating);
				ps.setString(2, uid);
				ps.setString(3, mid);
				ps.setString(3, comment);
				ps.executeUpdate();
				 
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  } catch (Exception e) {
			  e.printStackTrace();}
		  return true;
		}else {
		return false;}
		 
}
	
	
	public boolean check_rate(int rating, String uid, String mid, String comment){
		 if(rating == 0 & comment == ""){ 
			 return true;
			 }
		 else
			 return false;
		 
		}
	
	
	
	//Check if movie already exist
		public boolean checkMovie(String title, int publishingDate) {

			// Declare necessary SQL query.
			String queryMD = "SELECT FROM mydb01.movie WHERE title=? AND publishingDate=?";

			// query data.
			try (Connection connection = DriverManager
					.getConnection(
							"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
									+ Configuration.getPort() + "/" + Configuration.getDatabase(),
							Configuration.getUser(), Configuration.getPassword())) {
				try (PreparedStatement psSelect = connection.prepareStatement(queryMD)) {
					psSelect.setString(1, title);
					psSelect.setInt(2, publishingDate);
					try (ResultSet rs = psSelect.executeQuery()) {
						return rs.next();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		public boolean addMovie(String title, String director, String actors, TimeData publishingDate) {
			 
			String sqlInsertM = "INSERT INTO mydb01.movie (title, director, actors, publishingDate) VALUES (?,?,?,?)";
	        
			Boolean notExist=checkMovie(title, publishingDate);
			if(notExist)
			{
			try (Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydb01")) {
	            
				try (PreparedStatement ps = connection.prepareStatement(sqlInsertM)) {
					ps.setString(1, title);
					ps.setString(2, director);
					ps.setString(3, actors);
					ps.setString(4, publishingDate);
					ps.executeUpdate();
					 
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			  } catch (Exception e) {
				  e.printStackTrace();}
			  return true;
			}else {
			return false;}
			 
	}
		
		
		public boolean checkMovie(String title, TimeData publishingDate) {
			 if(checkMovie(title, publishingDate) == true)
			 { return true;}
			 else
				 return false;
			}

		
		
		
	
}
