package com.ltnc.be.domain.receivedPatient;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class ReceivedPatientKey implements Serializable {
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "patient_id")
    private Long patientId;
}
