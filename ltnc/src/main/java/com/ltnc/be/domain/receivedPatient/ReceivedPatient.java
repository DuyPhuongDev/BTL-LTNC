package com.ltnc.be.domain.receivedPatient;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ReceivedPatient {
    @EmbeddedId
    private ReceivedPatientKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "room")
    private String room;

    @Column(name="receive_at")
    private long receiveAT;
}
