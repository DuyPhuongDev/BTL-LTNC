package com.ltnc.be.domain.patient;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.attendance.Attendance;
import com.ltnc.be.domain.user.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Patient extends BaseEntity {
  @Enumerated(EnumType.STRING)

  @Column(name = "dob")
  @Temporal(TemporalType.DATE)
  private Date dob;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "vssId")
  private String vssId;

  @Column(name = "patientType")
  private PatientType patientType;

  @Column(name = "medicalRecord")


  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<Attendance> authProviders;


}
