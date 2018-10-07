package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.service.AdminService;
import org.hibernate.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/secured/admin")
@RestController
public class AdminController {

    private final AdminService service;
    private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<User>> getDoctors() {
        logger.info("Fetching doctors for ADMIN");
        return service.getDoctors()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No registered doctors."));
    }

    @GetMapping("/labs")
    public ResponseEntity<List<User>> getLabs() {
        logger.info("Fetching labs for ADMIN");
        return service.getLabs()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No registered labs."));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createDoctorOrLab(@RequestBody User user) {
        logger.info("ADMIN creating [{}] with username [{}]", user.getRole(), user.getUsername());
        if (service.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Username already taken.");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createUser(user));
    }

    @Transactional
    @DeleteMapping("/remove/{name}")
    public void deleteDoctorOrLab(@PathVariable String name) {
        logger.info("Deleting user [{}]", name);
        service.deleteUser(name);
    }

    @GetMapping("/search/doctors")
    public ResponseEntity<List<User>> searchDoctorsByFullName(@RequestParam String query) {
        return service.searchDoctorsByFullName(query)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/labs")
    public ResponseEntity<List<User>> searchLabsByFullName(@RequestParam String query) {
        return service.searchLabsByFullName(query)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/validate/password")
    public boolean checkPasswordValidity(@RequestBody String password, Principal principal) {
        return this.service.checkPasswordValidityForUser(password, principal.getName());
    }

    @PostMapping("/change/password")
    public boolean changePassword(@RequestBody String newPassword, Principal principal) {
        logger.info("Changing password for user [{}]", principal.getName());
        return this.service.changePasswordForUser(newPassword, principal.getName()).getId() != 0;
    }

}
