
package com.ltnc.be.domain.patient;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;
import java.util.List;

@Table(name = "patients")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Patient extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "patientType")
    private PatientType patientType;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @NaturalId
    @Column(name = "vssId")
    private String vssId;
}