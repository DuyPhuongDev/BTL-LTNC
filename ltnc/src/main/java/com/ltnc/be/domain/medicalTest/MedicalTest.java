package com.ltnc.be.domain.medicalTest;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id",referencedColumnName = "id")
    private MedicalRecord medicalRecord;
}
