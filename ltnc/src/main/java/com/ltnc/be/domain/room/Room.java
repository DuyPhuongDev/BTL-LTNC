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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
@Builder
@Getter
public class Room extends BaseEntity {
    // capacity of the room
    @Column(name = "room_capacity")
    private int roomCapacity;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<RoomEmployee> roomEmployees;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<PatientRoom> patientRooms;
}
