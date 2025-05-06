package com.pm.patientService.PatientService.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pm.patientService.PatientService.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{


     boolean existsByEmail(String email);

     boolean existsByEmailAndIdNot(String email, Integer id);
}
