package com.sorsix.bloodscreening.repository;

import com.sorsix.bloodscreening.model.BloodTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodTestsRepository extends JpaRepository<BloodTest, Integer> {

    List<BloodTest> findAllByStatus(int i);

    Optional<BloodTest> findByPatientId(Integer id);
}
