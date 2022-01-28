import java.util.ArrayList;

import application.MRA_App;
import datatypes.UserData;
import dbadapter.Configuration;
import dbadapter.Movie;
import dbadapter.UD_Adapter;
import dbadapter.DB_Facade;
import org.junit.Before;
import org.junit.Test;

//import com.mysql.cj.xdevapi.Statement;

import org.junit.After;
import junit.framework.TestCase;


public class UserGUITest extends TestCase {

    public ArrayList<UserData>User;
    private UserData testU;

    @Before
    public void setUp(){


        UserData testU = new UserData ("demo", "demo", 20);
        ArrayList<UserData> testUser = new ArrayList<UserData>();
        MRA_App.getInstance().requestRegistration(name,email,age);
        
        //SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS userdata";
		String sqlCreateTableUser = "CREATE TABLE userdata (name varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, email varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL, age int NOT NULL );";
		String sqlInsertUser = "INSERT INTO userdata (name,email,age) VALUES (?,?,?)";

		// Perform database updates
		try (Connection connection = DriverManager
				.getConnection(
						"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
								+ Configuration.getPort() + "/" + Configuration.getDatabase(),
						Configuration.getUser(), Configuration.getPassword())) {

			try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
				psClean.executeUpdate();
			}
			try (PreparedStatement psCreateUser = connection.prepareStatement(sqlCreateTableUser)) {
				psCreateUser.executeUpdate();
			}
			try (PreparedStatement psInsertUser = connection.prepareStatement(sqlInsertUser)) {
				psInsertUser.setString(1, testU.getName());
				psInsertUser.setString(2, testU.getEmail());
				psInsertUser.setInt(3, testU.getAge());
				
				psInsertUser.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @Test
    public final void testAddUser(){
        String name = "demo";
        String email = "demo";
        int age = 20;
       
       // int uid = 1;

        ArrayList<UserData> test = MRA_App.getInstance().check_User(name,email,age);
        if(test.size() == 0){
            MRA_App.getInstance().forwardAddUser(name,email,age);
        }
    
        assertTrue(test.size() > 0);
        assertEquals(test.size(), MRA_App.getInstance().getAllUser().size());
    }

    @After
    public final void tearDown(){
        String sqlDelete = "DELETE FROM User";
        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
                                + Configuration.getPort() + "/" + Configuration.getDatabase(),
                        Configuration.getUser(), Configuration.getPassword())) {
            java.sql.Statement statement = connection.createStatement();
            statement.execute(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}