package com.example.demo.EmployeesAPI.repository;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.EmployeesAPI.data.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class EmployeesAPIRepository {
	public Employees[] getEmployeesList() throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		
		Employees[] employeesList = mapper.readValue(json, Employees[].class);
		
		return employeesList;
	}
	
}