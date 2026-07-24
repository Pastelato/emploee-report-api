package com.sm.employees.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sm.employees.dto.EmployeeSummaryDTO;
import com.sm.employees.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeSummaryDTO> getAllEmployeesSummary() {
        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeeSummaryDTO(
                        emp.getId(),
                        emp.getFirstName() + " " + emp.getLastName(), // Concatena nombre y apellido
                        emp.getDepartment()
                ))
                .toList();
    }


}
