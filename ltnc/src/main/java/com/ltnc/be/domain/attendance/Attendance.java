package com.ltnc.be.domain.attendance;

import com.ltnc.be.domain.BaseEntity;
import com.ltnc.be.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Table(name="attendances")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Attendance extends BaseEntity {
    @Column(name="beginTime")
    private long beginTime;
    @Column(name="endTime")
    private long endTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
