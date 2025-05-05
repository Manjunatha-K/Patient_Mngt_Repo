package com.pm.patientService.PatientService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientService.PatientService.dto.PatientRequestDTO;
import com.pm.patientService.PatientService.dto.PatientResponseDTO;
import com.pm.patientService.PatientService.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping("/findAllPatients")
	public ResponseEntity<List<PatientResponseDTO>> findAllPatients() {
		return new ResponseEntity<>(patientService.findAllPatients(), HttpStatus.OK);
	}

	@PostMapping("/AddPatient")
	public ResponseEntity<PatientResponseDTO> addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {

		PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
		return new ResponseEntity<PatientResponseDTO>(patientResponseDTO, HttpStatus.CREATED);
	}

}
