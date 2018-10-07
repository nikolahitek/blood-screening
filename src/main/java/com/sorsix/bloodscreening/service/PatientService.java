package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.repository.BloodTestsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final BloodTestsRepository bloodTestsRepository;

    public PatientService(BloodTestsRepository bloodTestsRepository) {
        this.bloodTestsRepository = bloodTestsRepository;
    }

    public Optional<BloodTest> getBloodTestForPatientId(Integer id) {
        return bloodTestsRepository.findByPatientId(id);
    }
}
