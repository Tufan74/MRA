package testing;

import java.util.Date;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

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


        m1 = new Movie ("Spiderman", "Sam Raimi", "Tobey Maguire", Date.valueOf("05/03/2002"), 1);
        m2 = new Movie ("Spiderman2022", "Anonymous", "Random passengers", Date.valueOf("05/03/2022"), 2);
        m3 = new Movie ("Spiderman2022", "Anonymous", "Random passengers", Date.valueOf("05/03/2022"), 3);
        ArrayList<Movie> testMovie = new ArrayList<Movie>();
        testMovie.add(m1).add(m2).add(m3);
        MRA_App.getInstance().addMovie(title, director, actors, publishingDate, mid);
        
        //SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS movie; Drop Table if exist rate";
		String sqlCreateTableMovie = "CREATE TABLE movie (title varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, director varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, actors varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, publishingDate date NOT NULL DEFAULT '00-00-0000'  mid int(11) NOT NULL AUTO_INCREMENT, PRIMARY KEY(id));"
				+ "CREATE TABLE  allratings(rating int(11), uid varchar(255) CHARACTER SET utf8_bin NOT NULL, mid varchar(255) CHARACTER SET utf8_bin NOT NULL, comment varchar(255) CHARACTER SET utf8_bin NOT NULL;";
		String sqlInsertMovie = "INSERT INTO movie (title, director, actors, publishingDate, mid) VALUES (?,?,?,?,?)";
		String sqlInsertRate = "INSERT INTO rate(rating, uid, mid, comment) VALUES (?,?,?,?)"
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
			for (Movie testM:testMovie) {
				try (PreparedStatement psInsertMovie = connection.prepareStatement(sqlInsertMovie)) {
					psInsertMovie.setString(1, testM.getTitle());
					psInsertMovie.setString(2, testM.getDirector());
					psInsertMovie.setString(3, testM.getActors());
					psInsertMovie.setDate(4, testM.getPublishingDate());
					psInsertMovie.setInt(5, testM.setMid());
					psInsertMovie.executeUpdate();
			}
		}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public final void testAddMovie(){
        String title = "Spiderman2022";
        String director = "Anonymous";
        String actors = "Random passenger";
        Date publishingDate = Date.valueOf("26/01/2022");
        int mid = 1;

        ArrayList<Movie> movies = MRA_App.getInstance().get_movies();
        
        List copy = new ArrayList(movies);
        Collections.sort(copy);
    
        assertTrue(test.size() > 0);
        assertEquals(movies.size(), 3);
        assertTrue(copy.equals(movies));
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