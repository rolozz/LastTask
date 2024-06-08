package kata.pp.bootstrap.bootstrap.controller;

import kata.pp.bootstrap.bootstrap.model.Role;
import kata.pp.bootstrap.bootstrap.model.User;
import kata.pp.bootstrap.bootstrap.service.RoleService;
import kata.pp.bootstrap.bootstrap.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> userList() {
        log.info("REST: {}", userService.getAll());
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userService.save(user);
        log.info("REST User created: {}", user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id, @RequestBody User user) {
        userService.update(user);
        log.info("REST User updated: {}", user);
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
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
