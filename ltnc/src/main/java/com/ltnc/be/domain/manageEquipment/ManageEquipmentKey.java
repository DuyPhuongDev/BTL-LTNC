package com.ltnc.be.domain.manageEquipment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ManageEquipmentKey implements Serializable {
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "equipment_id")
    private Long equipmentId;
}
