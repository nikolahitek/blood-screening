package com.sorsix.bloodscreening.service;

import com.sorsix.bloodscreening.model.BloodTest;
import com.sorsix.bloodscreening.model.User;
import com.sorsix.bloodscreening.repository.BloodTestsRepository;
import com.sorsix.bloodscreening.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final BloodTestsRepository bloodTestsRepository;
    private final UsersRepository usersRepository;

    public DoctorService(BloodTestsRepository bloodTestsRepository, UsersRepository usersRepository) {
        this.bloodTestsRepository = bloodTestsRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<List<BloodTest>> getActiveBloodTestsForDoctor(String name) {
        return Optional.of(getBloodTestsForDoctorWithStatus(name,1));
    }

    public Optional<List<BloodTest>> getFinishedBloodTestsForDoctor(String name) {
        return Optional.of(getBloodTestsForDoctorWithStatus(name,2));
    }

    public Optional<BloodTest> updateBloodTest(BloodTest updatedBloodTest) {
        return bloodTestsRepository.findById(updatedBloodTest.getId())
                .map(bloodTest -> {
                    bloodTest = updatedBloodTest;
                    return bloodTestsRepository.save(bloodTest);
                });
    }

    public Optional<BloodTest> getBloodTestForId(Integer id) {
        return bloodTestsRepository.findById(id);
    }

    private List<BloodTest> getBloodTestsForDoctorWithStatus(String doctorName, int status){
        int doctorId = getDoctorForUsername(doctorName)
                .map(User::getId)
                .orElse(-1);
        return bloodTestsRepository.findAllByStatus(status)
                .stream()
                .filter(test -> test.getDoctorId()==doctorId)
                .collect(Collectors.toList());
    }

    private Optional<User> getDoctorForUsername(String name){
        return usersRepository.findByUsername(name);
    }
}
