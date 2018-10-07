package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.repository.BloodTestsRepository;
import com.sorsix.bloodscreening.repository.UsersRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LabService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final BloodTestsRepository bloodTestsRepository;

    public LabService(PasswordEncoder passwordEncoder, UsersRepository usersRepository, BloodTestsRepository bloodTestsRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.bloodTestsRepository = bloodTestsRepository;
    }

    public Optional<List<User>> getDoctors() {
        return Optional.of(usersRepository.findAllByRole("DOCTOR"));
    }

    public Optional<User> createTokenForPatient(User patient) {
        String password = generateRandomString();
        patient.setPassword(passwordEncoder.encode(password));
        patient.setUsername(generateRandomString());
        patient = usersRepository.save(patient);
        patient.setPassword(password);
        return Optional.of(patient);
    }

    public Optional<BloodTest> startBloodTestInLab(BloodTest bloodTest, String labName){
        bloodTest.setLabId(usersRepository.findByUsername(labName).get().getId());
        return Optional.of(bloodTestsRepository.save(bloodTest));
    }

    private String generateRandomString(){
        return RandomStringUtils.randomAlphanumeric(5,8);
    }

    public Optional<List<BloodTest>> getActiveBloodTests(String labName) {
        return Optional.of(getBloodTestsForLabWithStatus(labName,0));
    }

    public Optional<List<BloodTest>> getFinishedBloodTests(String labName) {
        List<BloodTest> allFinishedTests = getBloodTestsForLabWithStatus(labName,1);
        allFinishedTests.addAll(getBloodTestsForLabWithStatus(labName,2));
        return Optional.of(allFinishedTests);
    }

    private Optional<User> getLabForUsername(String name){
        return usersRepository.findByUsername(name);
    }

    private List<BloodTest> getBloodTestsForLabWithStatus(String labName, int status){
        int labId = getLabForUsername(labName)
                .map(User::getId)
                .orElse(-1);
        return bloodTestsRepository.findAllByStatus(status)
                .stream()
                .filter(test -> test.getLabId()==labId)
                .collect(Collectors.toList());
    }

    public Optional<BloodTest> updateBloodTest(BloodTest updatedBloodTest){
        return bloodTestsRepository.findById(updatedBloodTest.getId())
                .map(bloodTest -> {
                    bloodTest = updatedBloodTest;
                    return bloodTestsRepository.save(bloodTest);
                });
    }

    public Optional<BloodTest> getBloodTestForId(Integer id) {
        return bloodTestsRepository.findById(id);
    }
}
