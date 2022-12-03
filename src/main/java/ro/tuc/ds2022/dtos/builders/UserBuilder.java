package ro.tuc.ds2022.dtos.builders;

import org.springframework.security.core.userdetails.UserDetails;
import ro.tuc.ds2022.dtos.ClientDto;
import ro.tuc.ds2022.dtos.UserDto;
import ro.tuc.ds2022.entities.Client;
import ro.tuc.ds2022.entities.User;

public class UserBuilder {

    public static UserDto toUserDto(UserDetails user){
        return new UserDto(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(),user.getRole());
    }
}
