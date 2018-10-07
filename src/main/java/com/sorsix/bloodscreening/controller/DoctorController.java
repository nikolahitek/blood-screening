package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.handler.exception.DataBaseTransactionException;
import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.service.DoctorService;
import org.hibernate.PropertyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("hasAnyRole('DOCTOR')")
@RequestMapping("/secured/doctor")
@RestController
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping("/bloodTests/active")
    public ResponseEntity<List<BloodTest>> getActiveBloodTests(Principal principal){
        return service.getActiveBloodTestsForDoctor(principal.getName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No active blood tests."));
    }

    @GetMapping("/bloodTests/finished")
    public ResponseEntity<List<BloodTest>> getFinishedBloodTests(Principal principal){
        return service.getFinishedBloodTestsForDoctor(principal.getName())
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("No finished blood tests."));
    }

    @Transactional
    @PatchMapping("bloodTest/comment")
    public ResponseEntity<BloodTest> commentBloodTest(@RequestBody BloodTest bloodTest){
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
