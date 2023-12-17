package com.example.demo.EmployeesAPI.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.EmployeesAPI.data.Attendance;
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

	public List<Employees> getEmployee(int employeeId) throws IOException {

		Employees[] employeeInfo = employeesAPIRepository.getEmployee(employeeId);

		return Arrays.asList(employeeInfo);
	}

	public List<Attendance> getAttendance(int employeeId) throws IOException {

		Attendance[] employeeAttendance = employeesAPIRepository.getAttendance(employeeId);

		return Arrays.asList(employeeAttendance);
	}

	public void postEmployee(String name, String hometown, String joiningMonth) throws IOException {
		employeesAPIRepository.postEmployee(name, hometown, joiningMonth);
	}

	public void postStatus(int employeeId, String statusButton) throws IOException {
		
	    LocalDateTime getDate = LocalDateTime.now();
	    String currentTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(getDate);

		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";

		switch (statusButton) {
		case "clockIn":
			clockIn = currentTime;
			break;

		case "breakStart":
			breakStart = currentTime;
			break;

		case "breakEnd":
			breakEnd = currentTime;
			break;

		case "clockOut":
			clockOut = currentTime;
			break;
		}

		employeesAPIRepository.postStatus(employeeId, clockIn, breakStart, breakEnd, clockOut);
	}
}