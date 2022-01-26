package testing;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.xdevapi.Statement;

import application.MRA_App;


import org.junit.After;
import dbadapter.MovieRate;
import dbadapter.Configuration;
import dbadapter.MD_Adapter;



public class T_RateMovie extends TestCase{
	
	private ArrayList<MovieRate> MovieRate;
	private MovieRate testR;
	
	@Before
	public void setUp() {
		MovieRate testR;
		
		testR = new MovieRate(8, "Tufan Özdemir", "John Wick 3","comment test");
		ArrayList<MovieRate> testRate = new ArrayList<MovieRate>();
		MD_Adapter.getInstance().rate(rating, uid, mid, comment);
		
		// SQL statements
		
		String sqlCleanDB = "DROP TABLE IF EXISTS rate";
		String sqlCreateTableRate = "CREATE TABLE  allratings(rating int(11), uid varchar(255) CHARACTER SET utf8_bin NOT NULL, mid varchar(255) CHARACTER SET utf8_bin NOT NULL, comment varchar(255) CHARACTER SET utf8_bin NOT NULL;";  
		String sqlInsertRate = "INSERT INTO rate(rating, uid, mid, comment) VALUES (?,?,?,?)";
		
		
		// Perform database updates
		
		try(Connection connection = DriverManager.
				getConnection(
				"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
						+ Configuration.getPort() + "/" + Configuration.getDatabase(),
				Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreateRate = connection.prepareStatement(sqlCreateTableRate)) {
				psCreateRate.executeUpdate();
			}
			try (PreparedStatement psInsertRate = connection.prepareStatement(sqlInsertRate)) {
				psInsertRate.setInt(1, testR.getRating());
				psInsertRate.setString(2, testR.getUid());
				psInsertRate.setString(3, testR.getMid());
				psInsertRate.setString(4, testR.getComment());
				psInsertRate.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
	
	@Test
	public final void testRateMovie() {
		
		int rating = 8;
		String uid = "Tufan Özdemir";
		String mid = "John Wick 3";
		String comment = "comment test";
		
		ArrayList<MovieRate> test = MD_Adapter.getInstance().check_rate(rating,uid,mid,comment);
		if(test.size() == 0 ) {
			MRA_App.getInstance().addRating(rating,uid,mid,comment);
		}
		assertTrue(test.size() > 0);
		assertEquals(MovieRate.size(), MD_Adapter.getInstance().getAllRating().size());
	}
	
	@After
	public final void tearDown() {
		String sqlDelete = "DELETE FROM MovieRate";
		 try (Connection connection = DriverManager
	                .getConnection(
	                        "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
	                                + Configuration.getPort() + "/" + Configuration.getDatabase(),
	                        Configuration.getUser(), Configuration.getPassword())) {
	            Statement statement = connection.createStatement();
	            statement.execute(sqlDelete);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	}
	
	

		
