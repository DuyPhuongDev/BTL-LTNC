package com.ltnc.be.domain.employee;


import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.admin.Admin;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicine.Medicine;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends User{
    @Column(name="duty", nullable = false)
    private DutyType duty;

    @Column(name="degree", nullable = false)
    private DegreeType degree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id", nullable=false)
    private Admin admin;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "handle_record",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "record_id")
    )
    private List<MedicalRecord> medicalRecords;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "manage_medicine",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_name")
    )
    private List<Medicine> medicineList;
}
