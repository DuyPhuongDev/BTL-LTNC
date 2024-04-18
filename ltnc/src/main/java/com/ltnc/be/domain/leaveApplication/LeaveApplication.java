package com.ltnc.be.domain.leaveApplication;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "leave_application")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplication extends BaseEntity {
    @Column(name = "reason")
    private String reason;

    @Column(name = "time_off")
    @Temporal(TemporalType.DATE)
    private Date timeOff;

    @Column(name = "time_send")
    @Temporal(TemporalType.DATE)
    private Date timeSend;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
