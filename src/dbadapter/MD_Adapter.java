package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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
	
	
	// adds the rating in the database
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
	
	
	// check if RegisteredUser can rate a movie (not complete because can rate more than 1 time but works) 
	public boolean check_rate(int rating, String uid, String mid, String comment){
		 
		
		if(rating >= 1 && rating <= 10){ 
			 return true;
			 }
		 else
			 return false;
		 
		}
	
	
	
	
	public boolean addMovie(String title, String director, String actors, Date publishingDate, int mid) {
		String sqlInsert = "INSERT INTO mydb.01.movie (title, director, actors, publishingDate, mid) VALUES (?,?,?,?,?)";
		
		Boolean notExist = check_movie(title, director, actors, publishingDate, mid);
		if (notExist) {
			try (Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/mydb01")){
				try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
					ps.setString(1, title);
					ps.setString(2, director);
					ps.setString(3, actors);
					ps.setDate(4, (java.sql.Date) publishingDate);
					ps.setInt(5, mid);
					ps.executeUpdate();
				}
				
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				return true;
			
		} else {
			return false;
		}
		
		
		
	}
	
	public boolean check_movie(String title, String director, String actors, Date publishingDate, int mid) {
		if(publishingDate == null & title == "") {
			return true;
			
		}
		else
			return false;
	}
	
		public List<Movie> get_movies(){
		List<Movie> movies = new ArrayList<Movie>();
		String queryMD = "SELECT mid, title, director, actors, publishingDate, avgRate FROM mydb01.movie";
		
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {
			PreparedStatement ps = connection.prepareStatement(queryMD);

				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Timestamp ts = rs.getTimestamp(4);
						Date date = new Date(ts.getTime());
//						TimeData td = new TimeData(time.substring(0, 3), time.substring(5, 6), time.substring(8, 9));
						Movie movie = new Movie( rs.getString(1), rs.getString(2), date,rs.getInt(0),rs.getString(3), rs.getDouble(5));
						movies.add(movie);
					}
				}
			}
		 catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return movies;
		}
	
}


