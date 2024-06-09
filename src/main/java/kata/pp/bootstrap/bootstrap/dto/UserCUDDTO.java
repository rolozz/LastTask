package kata.pp.bootstrap.bootstrap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kata.pp.bootstrap.bootstrap.model.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCUDDTO {

    Long id;
    String username;
    String password;
    String email;
    Set<Role> roles;

}
