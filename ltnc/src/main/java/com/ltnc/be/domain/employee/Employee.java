package com.ltnc.be.domain.employee;

import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.medicineManagement.MedicineManagement;
import com.ltnc.be.domain.patientEmployee.PatientEmployee;
import com.ltnc.be.domain.room.Room;
import com.ltnc.be.domain.prescription.Prescription;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import com.ltnc.be.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
@Data
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
}
