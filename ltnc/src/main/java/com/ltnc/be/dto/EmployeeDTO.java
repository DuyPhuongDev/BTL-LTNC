package com.ltnc.be.dto;

import com.ltnc.be.domain.employee.DegreeType;
import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.task.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String employeeName;
    private DutyType dutyType;
    private DegreeType degreeType;
    private String sex;
    private String address;
    private String phoneNumber;
    private Date timeStart;
    private Date timeEnd;
    private List<EquipmentDTO> equipments;
    private List<PrescriptionDTO> prescriptions;
    private List<MedicalRecordDTO> medicalRecords;
    private List<MedicineManagementDTO> medicineManagements;
    private List<TaskDTO> tasks;

    public static EmployeeDTO fromDomain(Employee employee) {
        return EmployeeDTO.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getFullName())
                .dutyType(employee.getDutyType())
                .degreeType(employee.getDegreeType())
                .address(employee.getAddress())
                .sex(employee.getSex())
                .phoneNumber(employee.getPhoneNumber())
                .timeStart(employee.getTimeStart())
                .timeEnd(employee.getTimeEnd())
                .tasks(employee.getTasks().stream().map(TaskDTO::fromDomain).collect(Collectors.toList()))
                .equipments(employee.getEquipments().stream().map(EquipmentDTO::fromDomain).collect(Collectors.toList()))
                .prescriptions(employee.getPrescriptions().stream().map(PrescriptionDTO::fromDomain).collect(Collectors.toList()))
                .medicalRecords(employee.getMedicalRecords().stream().map(MedicalRecordDTO::fromDomain).collect(Collectors.toList()))
                .medicineManagements(employee.getMedicineManagementList().stream().map(MedicineManagementDTO::fromDomain).collect(Collectors.toList()))
                .build();
    }
}
