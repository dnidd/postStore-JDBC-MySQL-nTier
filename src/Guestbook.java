import java.sql.Connection;

/**
 * Entry point.
 * DBConnector: holds connection to database/server.
 * .connectDb(): actual connects.
 * GuestRepository: queries to database.
 * UI: terminal ui.
 */
public class Guestbook {

    public static void main(String[] args) {

        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.connectDb();

        if (connection == null) {
            return;
        }

        // Injects connection into repository
        GuestRepository repo = new GuestRepository(connection);

        UI ui = new UI(dbConnector, repo);
        ui.startUI();

    }
}