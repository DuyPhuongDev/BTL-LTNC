package com.ltnc.be.domain.admin;

import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "admins")
@Entity
public class Admin extends User{
    @OneToMany(mappedBy = "id")
    private List<Employee> employees;
}
