package com.pm.patientService.PatientService.controller;

import java.util.List;

import com.pm.patientService.PatientService.dto.vlaidators.CreatePatientValidationGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pm.patientService.PatientService.dto.PatientRequestDTO;
import com.pm.patientService.PatientService.dto.PatientResponseDTO;
import com.pm.patientService.PatientService.service.PatientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for Managing Patients")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping("/findAllPatients")
	@Operation(summary = "Get Patients")
	public ResponseEntity<List<PatientResponseDTO>> findAllPatients() {
		return new ResponseEntity<>(patientService.findAllPatients(), HttpStatus.OK);
	}

	@PostMapping("/AddPatient")
	@Operation(summary = "Create a new Patient")
	public ResponseEntity<PatientResponseDTO> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {

		PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
		return new ResponseEntity<PatientResponseDTO>(patientResponseDTO, HttpStatus.CREATED);
	}

	@PutMapping("/UpdatePatient/{id}")
	@Operation(summary = "Update a Patient")
	public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") Integer id,@Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO){
		PatientResponseDTO patientResponseDTO = patientService.updatePatientService(patientRequestDTO, id);
		return new ResponseEntity<>(patientResponseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/DeletePatient/{id}")
	@Operation(summary = "Delete a Patient")
	public ResponseEntity<Void> DeletePatient(@PathVariable("id") Integer id){
		patientService.deletePatientService(id);
		return ResponseEntity.noContent().build();
	}

}
