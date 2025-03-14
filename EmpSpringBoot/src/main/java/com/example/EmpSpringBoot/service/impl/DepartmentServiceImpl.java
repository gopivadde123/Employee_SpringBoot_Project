package com.example.EmpSpringBoot.service.impl;

import com.example.EmpSpringBoot.dto.DepartmentDto;
import com.example.EmpSpringBoot.entity.Department;
import com.example.EmpSpringBoot.exception.ResourceNotFoundException;
import com.example.EmpSpringBoot.mapper.DepartmentMapper;
import com.example.EmpSpringBoot.repository.DepartmentRepository;
import com.example.EmpSpringBoot.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment=departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
      Department department=  departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department with id " + departmentId + " not found")
        );
        return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments= departmentRepository.findAll();
        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
       Department department= departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department with id " + departmentId + " not found")
        );
       department.setDepartmentName(updatedDepartment.getDepartmentName());
       department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
       Department savedDepartment=departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department with id " + departmentId + " not found")
        );
        departmentRepository.deleteById(departmentId);
    }
}
