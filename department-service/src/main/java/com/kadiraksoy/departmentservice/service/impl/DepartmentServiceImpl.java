package com.kadiraksoy.departmentservice.service.impl;

import com.kadiraksoy.departmentservice.dto.DepartmentDto;
import com.kadiraksoy.departmentservice.mapper.DepartmentMapper;
import com.kadiraksoy.departmentservice.model.Department;
import com.kadiraksoy.departmentservice.repository.DepartmentRepository;
import com.kadiraksoy.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode());

        departmentRepository.save(department);

        return departmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

        return departmentDto;
    }
}
