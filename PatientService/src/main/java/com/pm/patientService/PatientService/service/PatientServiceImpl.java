package com.pm.patientService.PatientService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.patientService.PatientService.dto.PatientRequestDTO;
import com.pm.patientService.PatientService.dto.PatientResponseDTO;
import com.pm.patientService.PatientService.model.Patient;
import com.pm.patientService.PatientService.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	PatientRepository patientRepository;

	@Autowired
	private ModelMapper mapper;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<PatientResponseDTO> findAllPatients() {
		List<PatientResponseDTO> patientDtos = patientRepository.findAll().stream()
				.map(patient -> mapper.map(patient, PatientResponseDTO.class)).collect(Collectors.toList());
		return patientDtos;
	}

	@Override
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		Patient patient = mapper.map(patientRequestDTO, Patient.class);
		patientRepository.save(patient);
		return mapper.map(patient, PatientResponseDTO.class);
	}

}
