package tests.database;

import org.testng.annotations.Test;
import utils.setup.BaseDbClass;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseTest extends BaseDbClass {
    @Test
    public void mysqlTest() {
        String mysqlDbUrl = configReader.getProperty("mysqlDbUrl");
        String mysqlDbUserName = configReader.getProperty("mysqlDbUserName");
        String mysqlDbPassword = configReader.getProperty("mysqlDbPassword");

//      create new table
        String createTableQuery = "CREATE TABLE IF NOT EXISTS users3 ("+
                "id INT AUTO_INCREMENT PRIMARY KEY,"+
                "name VARCHAR(100) NOT NULL,"+
                "email VARCHAR(100) NOT NULL,"+
                "age INT"+")";

        try {
            connection = DriverManager.getConnection(mysqlDbUrl, mysqlDbUserName, mysqlDbPassword);
            statement = connection.createStatement();
            statement.execute( createTableQuery );
            System.out.println( "Table created successfully!" );
        }catch (SQLException e){
            System.out.println("error creating table "+e.getMessage());
        }

    }
}
