package com.example.EmpSpringBoot.service.impl;

import com.example.EmpSpringBoot.dto.EmployeeDto;
import com.example.EmpSpringBoot.entity.Department;
import com.example.EmpSpringBoot.entity.Employee;
import com.example.EmpSpringBoot.exception.ResourceNotFoundException;
import com.example.EmpSpringBoot.mapper.EmployeeMapper;
import com.example.EmpSpringBoot.repository.DepartmentRepository;
import com.example.EmpSpringBoot.repository.EmployeeRepository;
import com.example.EmpSpringBoot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department= departmentRepository.findById(employeeDto.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("Department not found id:"+employeeDto.getDepartmentId()));
        employee.setDepartment(department);
        Employee savedEmployee=  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given id: " + employeeId)
        );
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Department department= departmentRepository.findById(updatedEmployee.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("Department not found id:"+updatedEmployee.getDepartmentId()));
       System.out.println("department----"+department);
        employee.setDepartment(department);
       Employee updatedEmployeeObj= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given id: " + employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}
