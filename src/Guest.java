
/**
 * Entity Guest used to populate table in database.
 * Not exposed 'above' Service layer.
 */
public class Guest {

    private int id;
    private String name;
    private String email;
    private String homepage;
    private String comment;

    /**
     * No args constructor.
     */
    public Guest() {}

    /**
     * Constructor
     * @param name = name
     * @param email = email
     * @param homepage = homepage
     * @param comment = comment
     */
    public Guest(String name, String email, String homepage, String comment) {
        this.name = name;
        this.email = email;
        this.homepage = homepage;
        this.comment = comment;
    }

    /**
     * All args constructor
     * @param id = id
     * @param name = name
     * @param email = email
     * @param homepage = homepage
     * @param comment = comment
     */
    public Guest(int id, String name, String email, String homepage, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.homepage = homepage;
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getHomepage() {
        return homepage;
    }


    public String getComment() {
        return comment;
    }


    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", homepage='" + homepage + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
