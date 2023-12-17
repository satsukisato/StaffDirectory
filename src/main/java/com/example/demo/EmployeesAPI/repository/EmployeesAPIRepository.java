package com.example.demo.EmployeesAPI.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.EmployeesAPI.data.Attendance;
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

	public Employees[] getEmployee(int employeeId) throws IOException {
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee?id="
				+ employeeId;

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employees[] employeeInfo = mapper.readValue(json, Employees[].class);

		return employeeInfo;
	}

	public Attendance[] getAttendance(int employeeId) throws IOException {
		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId="
				+ employeeId;

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Attendance[] employeeAttendance = mapper.readValue(json, Attendance[].class);

		return employeeAttendance;
	}

	public void postEmployee(String name, String hometown, String joiningMonth) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		String jsonPutData = "{"
				+ "\"body\": \"{"
				+ "\\\"name\\\":\\\"" + name + "\\\","
				+ "\\\"hometown\\\":\\\"" + hometown + "\\\","
				+ "\\\"joining_month\\\":\\\"" + joiningMonth + "/1" + "\\\""
				+ "}\""
				+ "}";

		RequestEntity<String> request = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON)
				.body(jsonPutData);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);
	}

	public void postStatus(int employeeId, String clockIn, String breakStart, String breakEnd, String clockOut)
			throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		String jsonPutData = "{"
				+ "\"body\": \"{"
				+ "\\\"employee_id\\\":\\\"" + employeeId + "\\\","
				+ "\\\"clock_in\\\":\\\"" + clockIn + "\\\","
				+ "\\\"break_start\\\":\\\"" + breakStart + "\\\","
				+ "\\\"break_end\\\":\\\"" + breakEnd + "\\\","
				+ "\\\"clock_out\\\":\\\"" + clockOut + "\\\""
				+ "}\""
				+ "}";

		RequestEntity<String> request = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON)
				.body(jsonPutData);

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);
	}

}