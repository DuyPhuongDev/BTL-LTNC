package com.ltnc.be.domain.manageWareHouse;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "manage_ware_house")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ManageWareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "employee_id")
    private Long employeeId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "equipment_id")
    private Long equipmentId;

    @Id
    @Column(name = "medicine_name")
    private String medicineName;


}
