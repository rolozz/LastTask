package kata.pp.bootstrap.bootstrap.controller;


import kata.pp.bootstrap.bootstrap.model.User;
import kata.pp.bootstrap.bootstrap.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user/api")
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> userInfo(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        log.info("REST Im a User: {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
