package com.ltnc.be.domain.patient;


import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.medicalRecord.MedicalRecord;
import com.ltnc.be.domain.medicalTest.MedicalTest;
import com.ltnc.be.domain.patientEmployee.PatientEmployee;
import com.ltnc.be.domain.patientRoom.PatientRoom;
import com.ltnc.be.domain.room.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "patient")
public class Patient extends BaseEntity {
    @Column(name = "BHYT", unique = true, nullable = false)
    private String BHYT;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "patient_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PatientType patientType;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "room_id")
    private Room patientRoom;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Column(name = "time_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date  timeEnd;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<MedicalTest> medicalTests;

    @OneToOne(mappedBy = "patient", fetch = FetchType.EAGER)
    private MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<PatientEmployee> patientEmployees;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<PatientRoom> patientRooms;
}
