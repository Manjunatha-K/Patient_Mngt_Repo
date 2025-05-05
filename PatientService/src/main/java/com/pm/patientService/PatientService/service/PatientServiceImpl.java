package com.pm.patientService.PatientService.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pm.patientService.PatientService.mapper.PatientMapper;
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

	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<PatientResponseDTO> findAllPatients() {
		List<PatientResponseDTO> patientDtos = patientRepository.findAll().stream()
				.map(patient -> PatientMapper.toDto(patient)).collect(Collectors.toList());
		return patientDtos;
	}

	@Override
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
		return PatientMapper.toDto(patientRepository.save(PatientMapper.toModel(patientRequestDTO)));
	}

}
