package com.example.demo.EmployeesAPI.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.EmployeesAPI.data.Attendance;
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

	@GetMapping("employeesDetail")
	public String doGet(@RequestParam(name = "employeeSelect") int employeeId, Model model) throws IOException {
		List<Employees> employeeInfo = employeesAPIService.getEmployee(employeeId);

		List<Attendance> employeeAttendance = employeesAPIService.getAttendance(employeeId);

		model.addAttribute("employeeInfo", employeeInfo);

		model.addAttribute("employeeAttendance", employeeAttendance);

		return "employeesDetail";
	}

	@GetMapping("employeesSignUp")
	public String getSignUp(Model model) throws IOException {
		return "employeesSignUp";
	}

	@PostMapping("employeesList")
	public String postEmployee(@RequestParam String name, @RequestParam String hometown,
			@RequestParam String joiningMonth, Model model) throws IOException {

		employeesAPIService.postEmployee(name, hometown, joiningMonth);
		//新しいURLにアクセスし、新しいページを表示
		return "redirect:/employeesList";
	}

	@PostMapping("/statusButton")
	public String postStatus(@RequestParam("employeeId") int employeeId,
			@RequestParam("statusButton") String statusButton, Model model) throws IOException {

		employeesAPIService.postStatus(employeeId, statusButton);

		return "redirect:/employeesDetail?employeeSelect=" + employeeId;
	}
}