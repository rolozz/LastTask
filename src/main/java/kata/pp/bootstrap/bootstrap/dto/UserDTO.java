package kata.pp.bootstrap.bootstrap.dto;

import kata.pp.bootstrap.bootstrap.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private Set<Role> role;

}
