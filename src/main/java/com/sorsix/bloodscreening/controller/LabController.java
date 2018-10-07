package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.handler.exception.DataBaseTransactionException;
import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.service.LabService;
import org.hibernate.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAnyRole('LAB')")
@RequestMapping("/secured/lab")
@RestController
public class LabController {

    private final static Logger logger = LoggerFactory.getLogger(LabController.class);
    private final LabService service;

    public LabController(LabService service) {
        this.service = service;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<User>> getDoctors() {
        return service.getDoctors()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No registered doctors."));
    }

    @PostMapping("/create/token")
    public ResponseEntity<User> createTokenForPatient(@RequestBody User patient){
        logger.info("Creating patient's token.");
        return service.createTokenForPatient(patient)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new PropertyNotFoundException("Token failed to create."));
    }

    @PostMapping("/bloodTest/start")
    public ResponseEntity<BloodTest> startBloodTest(@RequestBody BloodTest bloodTest, Principal principal){
        return service.startBloodTestInLab(bloodTest,principal.getName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new DataBaseTransactionException("Blood Test failed to create."));
    }

    @GetMapping("/bloodTests/active")
    public ResponseEntity<List<BloodTest>> getActiveBloodTests(Principal principal){
        return service.getActiveBloodTests(principal.getName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No active blood tests."));
    }

    @GetMapping("/bloodTests/finished")
    public ResponseEntity<List<BloodTest>> getFinishedBloodTests(Principal principal){
        return service.getFinishedBloodTests(principal.getName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No finished blood tests."));
    }

    @Transactional
    @PatchMapping("bloodTest/finish")
    public ResponseEntity<BloodTest> finishStartedBloodTest(@RequestBody BloodTest bloodTest){
        return service.updateBloodTest(bloodTest)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new DataBaseTransactionException("Error while updating Blood Test."));
    }

    @GetMapping("bloodTest/{id}")
    public ResponseEntity<BloodTest> getBloodTestForId(@PathVariable Integer id){
        return service.getBloodTestForId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("Blood Test for id " + id + "not found."));
    }
}
