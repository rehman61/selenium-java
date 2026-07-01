package utils.setup;

import org.testng.annotations.AfterMethod;
import utils.ConfigReader;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDbClass {
    protected ConfigReader configReader = new ConfigReader();
    protected Connection connection;
    protected Statement statement;

    @AfterMethod
    public void tearDown() throws SQLException {
        statement.close();
        connection.close();
    }
}
