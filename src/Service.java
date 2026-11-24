
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Service is responsible for data logic.
 */
public class Service {

    GuestRepository repo;
    Mapper mapper;


    public Service(GuestRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    /**
     * Guest input data converted to Guest entity before sent to repo.
     * Also validates input from HTML.
     * @param dto guest data input saved as dto.
     */
    public void addGuest(GuestDto dto) {
        validateInput(dto);

        Guest guest = mapper.toEntity(dto);
        repo.addGuest(guest);
    }


    /**
     * Fetch Guest entities, convert to dto's and collect in ArrayList.
     * @return ArrayList of guest dto.
     */
    public ArrayList<GuestDto> getAllGuests() {

        ArrayList<GuestDto> dtos = new ArrayList<>();

        try {
            ResultSet rs = repo.getAllPosts();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String homepage = rs.getString("homepage");
                String comment = rs.getString("comment");

                Guest guest = new Guest(id, name, email, homepage, comment);

                // Convert entity to Dto to not expose entities.
                GuestDto dto = mapper.toDto(guest);

                dtos.add(dto);
            }

        return dtos;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    /**
     * Helper method validates if HTML input.
     * regex finds: <any characters>, replaces with "censored".
     * @param dto = guest Dto.
     */
    private void validateInput(GuestDto dto) {

        // regex pattern to use to censor HTML.
        Pattern p = Pattern.compile("<.*>");

        if (p.matcher(dto.getName()).find()) {
            dto.setName("censored");
        }
        if (p.matcher(dto.getEmail()).find()) {
            dto.setEmail("censored");
        }
        if (p.matcher(dto.getHomepage()).find()) {
            dto.setHomepage("censored");
        }
        if (p.matcher(dto.getComment()).find()) {
            dto.setComment("censored");
        }
    }


}
