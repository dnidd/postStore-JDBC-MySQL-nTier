import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Establish connection to database.
 * Database username & password as environment variables.
 * Responsible for closing connection.
 */
public class DBConnector {

    Connection connection;

    public Connection connectDb() {

        // allowMultiQueries=true
        // Allow multiple placeholder queries from populateStartDataToDB() in GuestRepository.
        String url = "jdbc:mysql://localhost:3306/guestbook?allowMultiQueries=true";

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");

        if (username == null || password == null) {
            System.out.println("Please provide DB_USERNAME and DB_PASSWORD as environment variables.");
            return null;
        }

        // Manage connection to a specific db.
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }


    /**
     * Closing connection to DB server.
     */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
