package com.kadiraksoy.employeeservice.service.impl;

import com.kadiraksoy.employeeservice.dto.APIResponseDto;
import com.kadiraksoy.employeeservice.dto.DepartmentDto;
import com.kadiraksoy.employeeservice.dto.EmployeeDto;
import com.kadiraksoy.employeeservice.model.Employee;
import com.kadiraksoy.employeeservice.repository.EmployeeRepository;
import com.kadiraksoy.employeeservice.service.APIClient;
import com.kadiraksoy.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;


@Service

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    // private RestTemplate restTemplate;
    // private WebClient webClient;
    private final APIClient apiClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, APIClient apiClient) {
        this.employeeRepository = employeeRepository;
        this.apiClient = apiClient;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                saveDEmployee.getId(),
                saveDEmployee.getFirstName(),
                saveDEmployee.getLastName(),
                saveDEmployee.getEmail(),
                saveDEmployee.getDepartmentCode()
        );

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}