package kata.pp.bootstrap.bootstrap.controller;

import kata.pp.bootstrap.bootstrap.model.User;
import kata.pp.bootstrap.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequestMapping("/")
public class IndexController {
    private final UserService userService;
    //private final RoleService roleService;

    @Autowired
    public IndexController(UserService userService ) {
        this.userService = userService;
        //this.roleService = roleService;
    }

    @GetMapping("")
    public String showUsers(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        if (user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .contains("ROLE_ADMIN")) {
            model.addAttribute("page", "PAGE_ADMIN");
            return "redirect:/admin";
        }else  {
            model.addAttribute("page", "PAGE_USER");
            return "redirect:/user";}
    }
}

