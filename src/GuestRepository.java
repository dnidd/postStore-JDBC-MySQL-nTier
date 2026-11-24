import java.sql.*;

/**
 * Class handling CRUD operations to database.
 * For now write and read only.
 */
public class GuestRepository {

    Connection connection;

    public GuestRepository(Connection connection) {
        this.connection = connection;
    }


    /**
     * Fetch all posts from DB.
     * @return ResultSet of posts.
     */
    public ResultSet getAllPosts() {
        try {
            // statement objects returns resultset objects.
            // ResultSet: table representing the results returned from the db.
            PreparedStatement selectPs = connection.prepareStatement("SELECT * FROM guests");
            ResultSet rs = selectPs.executeQuery();

            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Adds guest entity to DB.
     * @param guestEntity = validated guest entity.
     */
    public void addGuest(Guest guestEntity) {
        String sql = """
                INSERT INTO guests (name, email, homepage, comment) VALUES (?, ?, ?, ?)
                """;
        // Inserts Strings at the right positions.
        try (PreparedStatement insertPs = connection.prepareStatement(sql)) {
            insertPs.setString(1, guestEntity.getName());
            insertPs.setString(2, guestEntity.getEmail());
            insertPs.setString(3, guestEntity.getHomepage());
            insertPs.setString(4, guestEntity.getComment());

            // .executeUpdate(); used for insert, update & delete CUD in CRUD
            insertPs.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Create table 'guests'.
     * Delete data on restart.
     * Populate database with placeholder guests with name, email, homepage, comment.
     */
    public void populateStartDataToDB() {

        String sql = """
                DROP TABLE IF EXISTS guests;
                
                CREATE TABLE IF NOT EXISTS guests (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(255),
                email VARCHAR(255),
                homepage VARCHAR(255),
                comment VARCHAR(255)
                );
                
                -- Emptying table if populated with old data
                TRUNCATE TABLE guests;
                
                INSERT INTO guests (name, email, homepage, comment)
                    VALUES (
                        'placeholder1',
                        'placeholder1@mail.com',
                        'placeholder1.com',
                        'placeholder1 comment'
                    );
        
                INSERT INTO guests (name, email, homepage, comment)
                    VALUES (
                        'placeholder2',
                        'placeholder2@mail.com',
                        'placeholder2.com',
                        'placeholder2 comment'
                    );
        
                INSERT INTO guests (name, email, homepage, comment)
                    VALUES (
                        'placeholder3',
                        'placeholder3@mail.com',
                        'placeholder3.com',
                        'placeholder3 comment'
                    );
                """;

        try (Statement statement = connection.createStatement()) {
            // execute is used when several different queries are used (drop, insert, (delete, update, select)).
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
