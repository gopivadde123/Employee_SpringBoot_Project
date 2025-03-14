package com.example.EmpSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmpSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpSpringBootApplication.class, args);
		Student student = new Student(1,"gopi");
		System.out.println(student.getName());
		System.out.println(student.getId());
	}

}
