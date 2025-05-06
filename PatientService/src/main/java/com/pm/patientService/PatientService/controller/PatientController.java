package com.pm.patientService.PatientService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PutMapping("/UpdatePatient/{id}")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") Integer id, @RequestBody PatientRequestDTO patientRequestDTO){
		PatientResponseDTO patientResponseDTO = patientService.updatePatientService(patientRequestDTO, id);
		return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
	}

}
