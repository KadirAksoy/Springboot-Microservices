package com.kadiraksoy.employeeservice.service;

import com.kadiraksoy.employeeservice.dto.APIResponseDto;
import com.kadiraksoy.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
