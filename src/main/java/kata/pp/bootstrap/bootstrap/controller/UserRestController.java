package kata.pp.bootstrap.bootstrap.controller;


import kata.pp.bootstrap.bootstrap.dto.UserRDTO;
import kata.pp.bootstrap.bootstrap.model.User;
import kata.pp.bootstrap.bootstrap.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<UserRDTO> userInfo(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        UserRDTO userRDTO = toUserRDTO(user);
        log.info("REST: {}", userRDTO);
        return new ResponseEntity<>(userRDTO, HttpStatus.OK);
    }

    private UserRDTO toUserRDTO(User user) {
        return modelMapper.map(user, UserRDTO.class);
    }

}
