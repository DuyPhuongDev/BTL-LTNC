package com.ltnc.be.domain.patientRoom;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient_room")
public class PatientRoom extends BaseEntity {
    @Column(name = "max_employee")
    private int maxEmployee;

    @Column(name = "bed_quantities")
    private int bedQuantities;

    @OneToMany(mappedBy = "patientRoom", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Patient> patients;

    @OneToMany(mappedBy = "patientRoom", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Employee> employees;
}
