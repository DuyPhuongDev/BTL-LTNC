package com.ltnc.be.domain.medicine;

import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Table(name = "medicines")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Medicine {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "medicine_type")
    private MedicineType medicineType;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_use_type")
    private MedicalUseType medicalUseType;

    @Column(name = "expiry_date", nullable = false)
    private Long expiryDate;

    @Column(name = "price")
    private String price;

    @ManyToMany(mappedBy = "medicineList")
    private List<Employee> employees;
    @PrePersist
    protected void prePersist() {
        if (this.expiryDate == null) expiryDate = Instant.now().toEpochMilli();
    }

    @PreUpdate
    protected void preUpdate() {
        this.expiryDate = Instant.now().toEpochMilli();
    }

    @PreRemove
    protected void preRemove() {
        this.expiryDate = Instant.now().toEpochMilli();
    }
}
