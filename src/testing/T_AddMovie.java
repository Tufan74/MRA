package testing;

import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import application.MRA_App;
import dbadapter.Configuration;
import dbadapter.Movie;
import dbadapter.MD_Adapter;
import org.junit.Before;
import org.junit.Test;

import com.mysql.cj.xdevapi.Statement;

import org.junit.After;
import junit.framework.TestCase;


public class T_AddMovie extends TestCase {

    public ArrayList<Movie> Movie;
    private Movie testM;

    @Before
    public void setUp(){
        Movie testM;


        testM = new Movie ("Spiderman", "Sam Raimi", "Tobey Maguire", Date.valueOf("05/03/2002"), 1);
        ArrayList<Movie> testMovie = new ArrayList<Movie>();
        MRA_App.getInstance().addMovie(title, director, actors, publishingDate, mid);
        
        //SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS movie";
		String sqlCreateTableMovie = "CREATE TABLE booking (title varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, director varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, actors varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, publishingDate date NOT NULL DEFAULT '00-00-0000'  mid int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY(id));";
		String sqlInsertMovie = "INSERT INTO movie (title, director, actors, publishingDate, mid) VALUES (?,?,?,?,?)";

		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreateMovie = connection.prepareStatement(sqlCreateTableMovie)) {
				psCreateMovie.executeUpdate();
			}
			try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
				psInsertMovie.setString(1, testM.getTitle());
				psInsertMovie.setString(2, testM.getDirector());
				psInsertMovie.setString(3, testM.getActors());
				psInsertMovie.setDate(4, testM.getPublishingDate());
				psInsertMovie.setInt(5, testM.setMid());
				psInsertMovie.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public final void testAddMovie(){
        String title = "Spiderman";
        String director = "Sam Raimi";
        String actors = "Tobey Maguire";
        Date publishingDate = Date.valueOf("05/03/2002");
        int mid = 1;

        ArrayList<Movie> test = MRA_App.getInstance().check_movie(title, director, actors, publishingDate, mid);
        if(test.size() == 0){
            MRA_App.getInstance().forwardAddNewMovie(title, director, actors, publishingDate, mid);
        }
    
        assertTrue(test.size() > 0);
        assertEquals(Movie.size(), MRA_App.getInstance().getAllMovie().size());
    }

    @After
    public final void tearDown(){
        String sqlDelete = "DELETE FROM Movie";
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