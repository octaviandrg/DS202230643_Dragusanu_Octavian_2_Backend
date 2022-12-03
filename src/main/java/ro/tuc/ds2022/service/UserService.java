package ro.tuc.ds2022.service;

import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.dtos.UserDto;
import ro.tuc.ds2022.dtos.builders.UserBuilder;
import ro.tuc.ds2022.entities.Role;
import ro.tuc.ds2022.entities.User;
import ro.tuc.ds2022.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    //@Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    private boolean usernameExist(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with username:" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(mapRoleToAuthorities(user.getRole())));

    }



    private SimpleGrantedAuthority mapRoleToAuthorities(Role role){
        return new SimpleGrantedAuthority(String.valueOf(role));
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll().stream().map(UserBuilder::toUserDto).collect(Collectors.toList());
    }

    public UserDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return new UserDto(user.getUsername(), user.getPassword(), user.getRole());
    }

    public Long createUser(UserDto userDto){
        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole());
        return userRepository.save(user).getId();
    }


    public UserDto updateUser(Long userId, UserDto userDto){
        User user = userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", "id", userId));
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());

        User updatedUser = userRepository.save(user);
        return new UserDto(updatedUser.getId(), updatedUser.getUsername(), passwordEncoder.encode(updatedUser.getPassword()), updatedUser.getRole());
    }

    public UserDto getUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("User with id " + id + " does not exist");
        }
        return UserBuilder.toUserDto(user.get());
    }

    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }


}
