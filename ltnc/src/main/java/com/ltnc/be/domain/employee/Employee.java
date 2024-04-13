package com.ltnc.be.domain.employee;

import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.leaveApplication.LeaveApplication;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.patientRoom.PatientRoom;
import com.ltnc.be.domain.prescription.Prescription;
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

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    private LeaveApplication leaveApplication;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "patientRoom_id")
    private PatientRoom patientRoom;

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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "manage_medicine",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> medicines;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "handle_record",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id")
    )
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Prescription> prescriptions;
}
