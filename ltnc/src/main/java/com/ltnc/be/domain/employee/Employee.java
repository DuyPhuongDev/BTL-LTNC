package com.ltnc.be.domain.employee;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.domain.patientEmployee.PatientEmployee;
import com.ltnc.be.domain.prescription.Prescription;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import com.ltnc.be.domain.task.Task;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends User {
    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DegreeType degreeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "duty_type")
    private DutyType dutyType;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<LeaveApplication> leaveApplicationList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<RoomEmployee> roomEmployee;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<PatientEmployee> patientEmployees;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;
    @Column(name = "time_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "manage_equipment",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> equipments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    private List<MedicineManagement> medicineManagementList;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "handle_record",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id")
    )
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "prescriber", fetch = FetchType.LAZY)
    private List<Prescription> prescriptions;

    // Manager relationship
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // Employees under this manager
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<Employee> employees;

    // task relationship
    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Employee(UserRole role, String username, String email, String sex, String password, String fullName, Date dob, String phoneNumber, String address, DegreeType degreeType, DutyType dutyType) {
        super(role, username, email, sex, password, fullName, dob, phoneNumber, address);
        this.degreeType = degreeType;
        this.dutyType = dutyType;
    }
}
