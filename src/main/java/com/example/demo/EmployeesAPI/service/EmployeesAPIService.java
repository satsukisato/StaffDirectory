package com.example.demo.EmployeesAPI.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.EmployeesAPI.data.Employees;
import com.example.demo.EmployeesAPI.repository.EmployeesAPIRepository;

@Service
public class EmployeesAPIService {

	private final EmployeesAPIRepository employeesAPIRepository;
	
	public EmployeesAPIService(EmployeesAPIRepository employeesAPIRepository) {
		this.employeesAPIRepository = employeesAPIRepository;
	}
	
	public List<Employees> getEmployeesList() throws IOException {
		
		Employees[] employeesList = employeesAPIRepository.getEmployeesList();
		
		return Arrays.asList(employeesList);
	}
	

}