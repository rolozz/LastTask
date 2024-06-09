package kata.pp.bootstrap.bootstrap.controller;

import kata.pp.bootstrap.bootstrap.dto.RoleDTO;
import kata.pp.bootstrap.bootstrap.dto.UserCUDDTO;
import kata.pp.bootstrap.bootstrap.dto.UserRDTO;
import kata.pp.bootstrap.bootstrap.model.Role;
import kata.pp.bootstrap.bootstrap.model.User;
import kata.pp.bootstrap.bootstrap.service.RoleService;
import kata.pp.bootstrap.bootstrap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/admin/api")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRDTO>> userList() {
        log.info("REST: {}", userService.getAll());
        List<UserRDTO> users = userService.getAll().stream().map(this::toUserRDTO).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRDTO> findUser(@PathVariable("id") Long id) {
        UserRDTO user = toUserRDTO(userService.findById(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserCUDDTO userCUDDTO) {
        userService.save(toUser(userCUDDTO));
        log.info("REST User created: {}", userCUDDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @PostMapping("/create/role")
    public ResponseEntity<HttpStatus> createRole(@RequestBody RoleDTO roleDTO) {
        roleService.addRole(toRole(roleDTO));
        log.info("REST Role created: {}", roleDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody UserCUDDTO userCUDDTO) {

        userService.update(toUser(userCUDDTO));
        log.info("REST User updated: {}", userCUDDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        String result = userService.findById(id).toString();
        userService.deleteById(id);
        log.info("REST: User deleted: {}", result);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/roles")
    public List<RoleDTO> getRoles() {
        List<RoleDTO> roles = roleService.getRoles().stream().map(role -> modelMapper.map(role, RoleDTO.class)).toList();
        return roles;

    }

    private User toUser(UserCUDDTO userCUDDTO) {
        return modelMapper.map(userCUDDTO, User.class);
    }

    private Role toRole(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

    private UserRDTO toUserRDTO(User user) {
        return modelMapper.map(user, UserRDTO.class);
    }


}
