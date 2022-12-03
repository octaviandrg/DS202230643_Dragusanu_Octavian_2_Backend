package ro.tuc.ds2022.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import ro.tuc.ds2022.entities.Role;

import java.util.Collection;
import java.util.List;

public class UserDto {

    private Long id;
    private String username;
    private String password;

    private Role role;

    private List<GrantedAuthority> authorities;

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public UserDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {

        this.username = username;
        this.password = password;
        this.role = getRoleFromAuthorities(authorities.toString());
    }


    public Role  getRoleFromAuthorities(String authorities) {
        if (authorities.equals("[ADMIN]")) {
            return Role.ADMIN;
        }
        return Role.REGULAR;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDto(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDto(String username, String password, Role role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }

//    public UserDto(String username, String password, List<Role> role) {
//        this.username = username;
//        this.password = password;
//        this.role = role.get(0);
//    }
//
//    public UserDto(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }
    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
