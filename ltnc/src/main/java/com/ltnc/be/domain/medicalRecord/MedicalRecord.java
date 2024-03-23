package com.ltnc.be.domain.medicalRecord;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "medical_records")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MedicalRecord extends BaseEntity {
    @Column(name ="diagnostic")
    private String diagnostic;
    @Column(name="hospitalized_time")
    private long hospitalizedTime;
    @Column(name="leave_time")
    private long leaveTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToMany(mappedBy = "medicalRecords")
    private List<Employee> employees;
}
