package com.ltnc.be.port.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.domain.employee.Employee;
import com.ltnc.be.rest.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeFacade {
    List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy);

    List<EmployeeResponse> searchEmployees(String name, DutyType dutyType, Integer pageNo, Integer pageSize, String sortBy);

    List<EmployeeResponse> getAllEmployeesManagedByEmployee(Long employeeId, Integer pageNo, Integer pageSize, String sortBy);

    List<EmployeeResponse> searchAllEmployeesManagedByEmployee(Long employeeId, String name, DutyType dutyType, Integer pageNo, Integer pageSize, String sortBy);
}
