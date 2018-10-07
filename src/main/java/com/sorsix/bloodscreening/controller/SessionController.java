package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class SessionController {

    private final UserDetailsServiceImpl service;
    private final static Logger logger = LoggerFactory.getLogger(SessionController.class);

    public SessionController(UserDetailsServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/session/user")
    @ResponseBody
    public User currentUser(Principal principal) {
        logger.info("Fetching current (logged in) user object.");
        if (principal == null) return new User();
        return service.getCurrentUser(principal.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username [" + principal.getName() + "] does not exist."));
    }
}
