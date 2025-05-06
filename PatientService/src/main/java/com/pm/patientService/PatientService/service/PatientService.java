package com.pm.patientService.PatientService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patientService.PatientService.dto.PatientRequestDTO;
import com.pm.patientService.PatientService.dto.PatientResponseDTO;

@Service
public interface PatientService {
	public List<PatientResponseDTO> findAllPatients();
	
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

	public PatientResponseDTO updatePatientService(PatientRequestDTO patientRequestDTO, Integer id);

	public void deletePatientService(Integer id);
}
