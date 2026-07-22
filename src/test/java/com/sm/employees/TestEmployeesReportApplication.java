package com.sm.employees;

import org.springframework.boot.SpringApplication;

public class TestEmployeesReportApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeesReportApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
