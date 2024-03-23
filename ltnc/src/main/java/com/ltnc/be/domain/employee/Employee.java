package com.ltnc.be.domain.employee;

import com.ltnc.be.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "employees")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Employee extends User {
  @Column(name = "duty", nullable = false)
  private DutyType duty;

  @Column(name = "degree", nullable = false)
  private DegreeType degree;
}
