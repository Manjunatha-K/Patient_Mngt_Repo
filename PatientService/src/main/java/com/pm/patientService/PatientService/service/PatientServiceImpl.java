package com.pm.patientService.PatientService.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.pm.patientService.PatientService.exceptions.EmailAlreadyExistsException;
import com.pm.patientService.PatientService.exceptions.PatientNotFoundException;
import com.pm.patientService.PatientService.grpc.BillingServiceGrpcClient;
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

	private final BillingServiceGrpcClient billingServiceGrpcClient;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
		this.patientRepository = patientRepository;
		this.billingServiceGrpcClient = billingServiceGrpcClient;
	}

	public List<PatientResponseDTO> findAllPatients() {
		List<PatientResponseDTO> patientDtos = patientRepository.findAll().stream()
				.map(patient -> PatientMapper.toDto(patient)).collect(Collectors.toList());
		return patientDtos;
	}

	@Override
	public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

		if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
			throw new EmailAlreadyExistsException("Patient with Email already exists "+ patientRequestDTO.getEmail());
		}
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
		billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),newPatient.getName(),newPatient.getEmail());
		return PatientMapper.toDto(newPatient);
	}

	@Override
	public PatientResponseDTO updatePatientService(PatientRequestDTO patientRequestDTO, Integer id) {
		Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient with ID :"+id+" not found"));
		if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)){
			throw new EmailAlreadyExistsException("Patient with Email already exists "+ patientRequestDTO.getEmail());
		}
		patient.setName(patientRequestDTO.getName());
		patient.setEmail(patientRequestDTO.getEmail());
		patient.setAddress(patientRequestDTO.getAddress());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

		patientRepository.save(patient);

		return PatientMapper.toDto(patient);
	}

	@Override
	public void deletePatientService(Integer id) {
		patientRepository.deleteById(id);
	}

}
