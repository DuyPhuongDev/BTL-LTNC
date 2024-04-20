package com.ltnc.be.rest.response;

import com.ltnc.be.domain.employee.DegreeType;
import com.ltnc.be.domain.employee.Department;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.dto.*;
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
    private DegreeType degreeType;
    private String address;
    private String phoneNumber;
    private String sex;
    private Department department;
    private Date timeStart;
    private Date timeEnd;
    private List<TaskDTO> tasks;
    private List<EquipmentDTO> equipments;
    private List<PrescriptionDTO> prescriptions;
    private List<MedicalRecordDTO> medicalRecords;
    private List<MedicineManagementDTO> medicineManagements;

    public static EmployeeResponse toEmployeeResponse(com.ltnc.be.dto.EmployeeDTO employeeDTO) {
        return EmployeeResponse.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .employeeName(employeeDTO.getEmployeeName())
                .dutyType(employeeDTO.getDutyType())
                .degreeType(employeeDTO.getDegreeType())
                .address(employeeDTO.getAddress())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .timeStart(employeeDTO.getTimeStart())
                .sex(employeeDTO.getSex())
                .department(employeeDTO.getDepartment())
                .timeEnd(employeeDTO.getTimeEnd())
                .tasks(employeeDTO.getTasks())
                .equipments(employeeDTO.getEquipments())
                .prescriptions(employeeDTO.getPrescriptions())
                .medicalRecords(employeeDTO.getMedicalRecords())
                .medicineManagements(employeeDTO.getMedicineManagements())
                .build();
    }
}
