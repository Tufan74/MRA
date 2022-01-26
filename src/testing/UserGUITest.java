package testing;

import dbadapter.Configuration;
import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class UserGUITest {
    private WebTester webTester;

    @Before
    public final void prepare() {
        tearDown();
        webTester = new WebTester();
        webTester.setBaseUrl("http://localhost:8080/MovieRatingApplication_war_exploded/");
    }

    @Test
    public final void testRegistration() {
        String Name = "Test";
        String email = "email";
        String age = "age";

        webTester.setScriptingEnabled(false);
        webTester.beginAt("register");
        webTester.assertTitleEquals("Movie Rating App");
        webTester.assertFormPresent();
        webTester.assertTextPresent("name");
        webTester.assertTextPresent("Email");
        webTester.assertTextPresent("age");
        webTester.assertButtonPresentWithText("registration");

        webTester.setTextField("Name", Name);
        webTester.setTextField("email", email);
        webTester.setTextField("age", age);

        webTester.submit();

        webTester.assertCookiePresent("auth-user-id");
    }

    @After
    public final void tearDown() {
        String sqlDelete = "DELETE FROM user";
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