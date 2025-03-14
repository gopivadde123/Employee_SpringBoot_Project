package com.example.EmpSpringBoot.repository;

import com.example.EmpSpringBoot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
