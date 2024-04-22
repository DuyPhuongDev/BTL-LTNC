package com.ltnc.be.port.facade;

import com.ltnc.be.domain.room.Room;
import com.ltnc.be.rest.request.UpsertRoomRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomFacade {
    void createRoom(UpsertRoomRequest room);
    void deleteRoom(Long roomId);
    void assignPatientToRoom(Long roomId, Long patientId);
    void assignEmployeeToRoom(Long roomId, Long employeeId);

    @Transactional(readOnly = true)
    List<Room> getAllRooms();

    @Transactional
    void deleteRoomPatient(Long roomId, Long patientId);

    @Transactional
    void deleteRoomEmployee(Long roomId, Long employeeId);
}
