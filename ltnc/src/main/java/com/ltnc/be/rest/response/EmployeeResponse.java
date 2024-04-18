package com.ltnc.be.rest.response;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.dto.EquipmentDTO;
import com.ltnc.be.dto.MedicalRecordDTO;
import com.ltnc.be.dto.MedicineManagementDTO;
import com.ltnc.be.dto.PrescriptionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private String employeeName;
    private DutyType dutyType;
    private String address;
    private String phoneNumber;
    private Date timeStart;
    private Date timeEnd;
    private List<EquipmentDTO> equipments;
    private List<PrescriptionDTO> prescriptions;
    private List<MedicalRecordDTO> medicalRecords;
    private List<MedicineManagementDTO> medicineManagements;

    public static EmployeeResponse toEmployeeResponse(com.ltnc.be.dto.EmployeeDTO employeeDTO) {
        return EmployeeResponse.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .employeeName(employeeDTO.getEmployeeName())
                .dutyType(employeeDTO.getDutyType())
                .address(employeeDTO.getAddress())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .timeStart(employeeDTO.getTimeStart())
                .timeEnd(employeeDTO.getTimeEnd())
                .equipments(employeeDTO.getEquipments())
                .prescriptions(employeeDTO.getPrescriptions())
                .medicalRecords(employeeDTO.getMedicalRecords())
                .medicineManagements(employeeDTO.getMedicineManagements())
                .build();
    }
}
