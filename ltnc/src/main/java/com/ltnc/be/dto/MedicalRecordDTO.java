package com.ltnc.be.dto;

import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {
    private Long id;
    private String diagnostic;
    private Date hospitalizedTime;
    private Date leaveTime;
    private Long patientId;
    private String patientName;
    private List<Long> employeeIds;  // Simplifying employee details to IDs
    private List<Long> medicalTestIds;  // IDs of related medical tests
    private List<PrescriptionDTO> prescriptions;  // IDs of related prescriptions

    public static MedicalRecordDTO fromDomain(MedicalRecord medicalRecord) {
        return MedicalRecordDTO.builder()
                .id(medicalRecord.getId())
                .diagnostic(medicalRecord.getDiagnostic())
                .hospitalizedTime(medicalRecord.getHospitalizedTime())
                .leaveTime(medicalRecord.getLeaveTime())
                .patientId(medicalRecord.getPatient().getId())
                .patientName(medicalRecord.getPatient().getName())  // Ensure there is a getName method in Patient
                .employeeIds(medicalRecord.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()))
                .medicalTestIds(medicalRecord.getMedicalTests().stream().map(medicalTest -> medicalTest.getId()).collect(Collectors.toList()))
                .prescriptions(medicalRecord.getPrescriptions().stream().map(PrescriptionDTO::fromDomain).collect(Collectors.toList()))
                .build();
    }
}
