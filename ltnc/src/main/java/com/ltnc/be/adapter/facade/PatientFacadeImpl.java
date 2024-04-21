package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.patient.PatientType;
import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.dto.PatientDTO;
import com.ltnc.be.port.facade.PatientFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.port.repository.MedicalRecordRepository;
import com.ltnc.be.port.repository.PatientRepository;
import com.ltnc.be.rest.request.UpsertMedicalRecordRequest;
import com.ltnc.be.rest.request.UpsertPatientRequest;
import com.ltnc.be.rest.response.PatientResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PatientFacadeImpl implements PatientFacade {
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<PatientResponse> getAllPatients(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        var patients = patientRepository.findAll(pageable);
        if(patients.hasContent()){
            return patients.getContent().stream()
                    .map(PatientDTO::fromDomain)
                    .map(PatientResponse::toPatientResponse)
                    .collect(Collectors.toList());
        }else return new ArrayList<>();
    }

    @Override
    public List<PatientResponse> searchPatients(String name, PatientType patientType, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        var patients = patientRepository.findPatientBySearchAndCriteria(name,patientType,pageable);
        if(patients.hasContent()){
            return patients.getContent().stream()
                    .map(PatientDTO::fromDomain)
                    .map(PatientResponse::toPatientResponse)
                    .collect(Collectors.toList());
        }else return new ArrayList<>();
    }

    @Override
    @SneakyThrows
    public void savePatient(UpsertPatientRequest request) {
        Patient patient = Patient.builder()
                .name(request.getName())
                .BHYT(request.getBHYT())
                .phone(request.getPhone())
                .gender(request.getGender())
                .address(request.getAddress())
                .dob(request.getDob())
                .patientType(request.getType())
                .build();
        patientRepository.save(patient);
    }

    @Override
    @SneakyThrows
    public void deletePatientById(Long patientId) {
        Optional<Patient> optionalPatient = patientRepository.findPatientById(patientId);
        if (optionalPatient.isPresent()){
            patientRepository.deleteById(patientId);
        }else throw new EntityNotFoundException();
    }

    @Override
    @SneakyThrows
    public PatientResponse getPatient(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findPatientById(id);
        if (optionalPatient.isPresent()){
            return PatientResponse.toPatientResponse(PatientDTO.fromDomain(optionalPatient.get()));
        }else throw new EntityNotFoundException();
    }


}
