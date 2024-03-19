package com.ltnc.be.domain.medicalTest;

import com.ltnc.be.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "medical_tests")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MedicalTest extends BaseEntity {
    @Column(name = "medical_test_type")
    private MedicalTestType medicalTestType;
    @Column(name = "medical_test_result")
    private String result;
}
