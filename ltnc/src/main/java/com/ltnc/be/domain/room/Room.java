package com.ltnc.be.domain.room;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.patient.Patient;
import com.ltnc.be.domain.patientRoom.PatientRoom;
import com.ltnc.be.domain.roomEmployee.RoomEmployee;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room extends BaseEntity {
    // capacity of the room
    @Column(name = "room_capacity")
    private int roomCapacity;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<RoomEmployee> roomEmployees;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<PatientRoom> patientRooms;
}
