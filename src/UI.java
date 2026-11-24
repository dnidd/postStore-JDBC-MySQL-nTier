import java.util.ArrayList;
import java.util.Scanner;


/**
 * Terminal user interface.
 * User may choose to either create post in DB table or quit program.
 */
public class UI {

    private boolean run = true;
    private final DBConnector dbConnector;
    private final GuestRepository  repo;
    private final Service service;

    public UI(DBConnector dbConnector, GuestRepository repo) {
        this.dbConnector = dbConnector;
        this.repo = repo;
        Mapper mapper = new Mapper();
        this.service = new Service(repo, mapper);
    }


    /**
     * Start UI interface.
     * Takes input from user.
     */
    public void startUI() {

        // Populate DB with start up data.
        repo.populateStartDataToDB();

        Scanner scanner = new Scanner(System.in);

        while(run) {
            // Fetch and print guest dto's.
            fetchPosts();

            System.out.println("Add guest (1).");
            System.out.println("Quit (2).");

            int choice;

            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                // Takes care of new line char (\n) from Enter
                scanner.nextLine();


                // Create and add guest to table in database.
                if(choice == 1) {
                    System.out.println("Add name:");
                    String name = scanner.nextLine();
                    System.out.println("Add email:");
                    String email = scanner.nextLine();
                    System.out.println("Add homepage:");
                    String homepage = scanner.nextLine();
                    System.out.println("Add comment:");
                    String comment = scanner.nextLine();

                    // Adds guest to guests table in guestbook DB,
                    // via dto -> service -> Mapper -> validate HTML -> entity -> repository.

                    // Create Dto.
                    GuestDto dto = new GuestDto(name, email, homepage, comment);

                    service.addGuest(dto);
                }

                // Terminate app & close connection.
                else if(choice == 2) {
                    System.out.println("Shutting down...");
                    dbConnector.closeConnection();
                    run = false;
                }
                else {
                    System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("1 or 2 please");
                // clear scanner.
                scanner.nextLine();
            }
        }
        scanner.close();
    }


    /**
     * Helper method fetching Guest entities,
     * converting to Guest dto's from repository.
     * and prints the guest dto's.
     */
    public void fetchPosts() {

        ArrayList<GuestDto> dtos = service.getAllGuests();

        // Print Dto's
        for(GuestDto dto : dtos) {
            System.out.println(
                    "id: " + dto.getId()
                    + " | name: " +  dto.getName()
                    + " | email: " +  dto.getEmail()
                    + " | homepage: " + dto.getHomepage()
                    + " | comment: " + dto.getComment());
        }
    }



}
