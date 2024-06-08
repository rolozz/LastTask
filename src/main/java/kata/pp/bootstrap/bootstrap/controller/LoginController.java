package kata.pp.bootstrap.bootstrap.controller;

import kata.pp.bootstrap.bootstrap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {
UserService userService;
@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

        @GetMapping("/login")
    public String loginPage() {
        log.info("REST Login page");
        return "login";
    }
}
