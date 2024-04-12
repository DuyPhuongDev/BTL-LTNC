package com.ltnc.be.domain.medicalRecord;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.medicalTest.MedicalTest;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.prescription.Prescription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "medical_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord extends BaseEntity {
    @Column(name ="diagnostic")
    private String diagnostic;
    @Column(name="hospitalized_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hospitalizedTime;
    @Column(name="leave_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "handle_record",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    @OneToMany(mappedBy = "medicalRecord", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<MedicalTest> medicalTests;

    @OneToMany(mappedBy = "medicalRecord", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Prescription> prescriptions;
}
