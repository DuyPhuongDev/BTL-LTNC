package com.ltnc.be.domain.manageEquipment;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.equipment.Equipment;
import com.ltnc.be.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "manage_equipment")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ManageEquipment {
    @EmbeddedId
    private ManageEquipmentKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("equipmentId")
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
}
