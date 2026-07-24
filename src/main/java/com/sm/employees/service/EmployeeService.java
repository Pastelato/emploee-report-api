package com.sm.employees.service;

import java.util.List;

import com.sm.employees.dto.EmployeeSummaryDTO;


public interface EmployeeService {
    List<EmployeeSummaryDTO> getAllEmployeesSummary();
}
