package com.sorsix.bloodscreening.controller;

import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.service.PatientService;
import org.hibernate.PropertyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAnyRole('PATIENT')")
@RequestMapping("/secured/patient")
@RestController
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodTest> getBloodTestForPatient(@PathVariable Integer id){
        return service.getBloodTestForPatientId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PropertyNotFoundException("Blood Test for patient with id " + id + " not found."));
    }
}
