package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.employee.DutyType;
import com.ltnc.be.dto.EmployeeDTO;
import com.ltnc.be.port.facade.EmployeeFacade;
import com.ltnc.be.port.repository.EmployeeRepository;
import com.ltnc.be.rest.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeFacadeImpl implements EmployeeFacade {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var employees = employeeRepository.findAll(paging);
        if(employees.hasContent()){
            var employeesDto = employees.getContent().stream().map(EmployeeDTO::fromDomain).toList();
            return employeesDto.stream().map(EmployeeResponse::toEmployeeResponse).toList();
        }
        else{
            return new ArrayList<EmployeeResponse>();
        }
    }
    public List<EmployeeResponse> getEmployeesBySearchCriteria(String name, DutyType dutyType, Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        var employees = employeeRepository.findEmployeesBySearchCriteria(name, dutyType, paging);
        if(employees.hasContent()){
            var employeesDto = employees.getContent().stream().map(EmployeeDTO::fromDomain).toList();
            return employeesDto.stream().map(EmployeeResponse::toEmployeeResponse).toList();
        }
        else{
            return new ArrayList<EmployeeResponse>();
        }
    }
}
