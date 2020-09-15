package com.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.aspect.SplunkConn;
import com.employeeservice.model.Employee;
import com.employeeservice.model.Employees;
import com.employeeservice.service.EmployeeService;
import com.splunk.Receiver;
import com.splunk.Service;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	Service service= SplunkConn.getService();
	Receiver receiver = service.getReceiver();
	@Autowired
	EmployeeService empServ;
	@PostMapping("/saveEmp/{deptEmpFk}")
	
	public Employee saveEmp(@RequestBody Employee emp,@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "Save Employee Event in Employee Service");
		emp.setDeptEmpFk(deptEmpFk);
		empServ.createEmpServ(emp);
		
		return empServ.createEmpServ(emp);
	}
	@GetMapping("/listEmp/{deptEmpFk}")
	public Employees getAllEmp(@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "GetAll Employee Event in Employee Service");
		List<Employee> lisEmp = (List<Employee>) empServ.readEmpFromDeptServ(deptEmpFk);
		
		Employees emp =new Employees();
		emp.setEmployees(lisEmp);
		
		return emp;
	}
	
	@PutMapping("/updateEmp/{empId}")
	public boolean updateEmp(@RequestBody Employee emp,@PathVariable int empId)
	{
		//receiver.log("main", "Update Employee Event in Employee Service");
		emp.setEmpId(empId);
		empServ.updateEmpServ(emp);
		
		return true;
	}
	
	@DeleteMapping("/deleteEmp/{empId}/{deptEmpFk}")
	public boolean deleteEmp(@PathVariable int empId,@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "Delete Employee Event in Employee Service");
		empServ.deleteEmpFromDeptServ(empId, deptEmpFk);
		
		return true;
	}
	
	@DeleteMapping("/deleteEmp/{deptEmpFk}")
	public boolean deleteEmps(@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "Delete Employee Event in Employee Service");
		empServ.deleteEmpsFromDeptServ( deptEmpFk);
		
		return true;
	}
	
}
