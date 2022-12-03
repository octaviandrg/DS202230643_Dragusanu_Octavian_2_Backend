package ro.tuc.ds2022.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.tuc.ds2022.entities.User;



@Getter
@Setter
public class ClientDto {

    private Long id;

    private Long userId;

    private String firstName;

    private String lastName;

    private String address;

    public ClientDto() {
    }

    public ClientDto(Long userId, String firstName, String lastName, String address) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public ClientDto(Long id, Long userId, String firstName, String lastName, String address) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
