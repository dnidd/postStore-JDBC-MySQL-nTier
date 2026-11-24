
/**
 * Class mapping from entity to dto (data transfer object)
 * & from dto to entity.
 */
public class Mapper {


    /**
     * Map Guest entity to GuestDto.
     * @param guest = Guest entity.
     * @return GuestDto.
     */
    public GuestDto toDto(Guest guest) {
        GuestDto dto = new GuestDto();

        dto.setId(guest.getId());
        dto.setName(guest.getName());
        dto.setEmail(guest.getEmail());
        dto.setHomepage(guest.getHomepage());
        dto.setComment(guest.getComment());

        return dto;
    }


    /**
     * Map GuestDto to Guest entity.
     * @param guestDto = guest Dto.
     * @return Guest entity.
     */
    public Guest toEntity(GuestDto guestDto) {
        Guest entity = new Guest();

        entity.setId(guestDto.getId());
        entity.setName(guestDto.getName());
        entity.setEmail(guestDto.getEmail());
        entity.setHomepage(guestDto.getHomepage());
        entity.setComment(guestDto.getComment());

        return entity;
    }
}
