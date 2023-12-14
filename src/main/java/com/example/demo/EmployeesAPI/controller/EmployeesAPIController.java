package com.example.demo.EmployeesAPI.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.EmployeesAPI.data.Employees;
import com.example.demo.EmployeesAPI.service.EmployeesAPIService;

@Controller
public class EmployeesAPIController {

	private final EmployeesAPIService employeesAPIService;

	public EmployeesAPIController(EmployeesAPIService employeesAPIService) {
		this.employeesAPIService = employeesAPIService;
	}

	@GetMapping("employeesList")
	public String doGet(Model model) throws IOException {
		
		List<Employees> employees = employeesAPIService.getEmployeesList();

		model.addAttribute("employees", employees);
		
		return "employeesList";

	}
	



}