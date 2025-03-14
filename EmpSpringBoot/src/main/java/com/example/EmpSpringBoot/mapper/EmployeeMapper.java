package com.example.EmpSpringBoot.mapper;

import com.example.EmpSpringBoot.dto.EmployeeDto;
import com.example.EmpSpringBoot.entity.Employee;

public class EmployeeMapper {
        public static EmployeeDto mapToEmployeeDto(Employee employee){
            return new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartment().getId()
            );
        }
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee= new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        return employee;
    }
}
