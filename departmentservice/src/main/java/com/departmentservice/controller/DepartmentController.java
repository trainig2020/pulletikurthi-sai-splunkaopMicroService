package com.departmentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.aspect.SplunkConn;
import com.departmentservice.model.Department;
import com.departmentservice.model.Departments;
import com.departmentservice.model.Employee;
import com.departmentservice.model.Employees;
import com.departmentservice.service.DepartmentService;
import com.departmentservice.service.EmployeeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.splunk.Args;
import com.splunk.Receiver;
import com.splunk.Service;
@RestController
@RequestMapping("/department")
@RefreshScope
public class DepartmentController {
	Service service= SplunkConn.getService();
	Receiver receiver = service.getReceiver();
	
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeService employeeService;
	
	//@Value("${message}")
	
	private String message;
	@HystrixCommand(fallbackMethod = "saveFallBackMethod")
	@PostMapping("/saveDept")
	public Department saveDept(@RequestBody Department dept)
	{
		//receiver.log("main", "Save Department Event in Department Service");
		return departmentService.createDeptServ(dept);
		
	}
	
	
	public Department saveFallBackMethod(@RequestBody Department dept)
	{
		
		return new Department(0,"no dept available as of now","somewhere in world");
		
	}
	
	@GetMapping("/listDept")
	public Departments getAllDept()
	{
		//receiver.log("main", "GetAll Department Event in Department Service");
		List<Department> lis = departmentService.readAllDeptServ();
		Departments depts = new Departments();
		depts.setDepartments(lis);
		System.out.println("lis vals message"+message);
		return depts;
	}
	
	@PutMapping("/updateDept/{depId}")
	public boolean updateDept(@RequestBody Department dept,@PathVariable int depId)
	{
		//receiver.log("main", "Update Department Event  in Department Service");
		System.out.println("dept updating values"+depId + "name "+dept.getDeptName() +"loc "+dept.getDeptLoc());
		dept.setDeptId(depId);
		departmentService.updateDeptServ(dept);
		
		return true;
		
	}
	
	@DeleteMapping("/deleteDept/{depIds}")
	public boolean deleteDept(@PathVariable int depIds)
	{
		//receiver.log("main", "Delete Department Event  in Department Service");
		departmentService.delteDeptServ(depIds);
		employeeService.deleteEmpByDeptId(depIds);
		return true;
		
	}
	
	@GetMapping("/employee/listEmp/{deptId}")
	public Employees getEmployees(@PathVariable int deptId)
	{
		//receiver.log("main", "Get All Employee Event  in Department Service");
		Employees emp = employeeService.readEmpFromDeptServ(deptId);
		
		return emp;
		
	}
	
	@PostMapping("/employee/saveEmp/{deptEmpFk}")
	public Employee  saveEmp(@RequestBody Employee emp,@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "Save Employee Event  in Department Service");
		List<Department> lisDept = departmentService.readAllDeptServ();
		for (Department department : lisDept) {
			
			if(department.getDeptId() == deptEmpFk)
			{
				emp.setDeptEmpFk(deptEmpFk);
				
				
				return employeeService.createEmpServ(emp);
			}
		}
		return emp;
		
	}
	
	@PutMapping("/employee/updateEmp/{empId}")
	public String updateEmp(@RequestBody Employee emp,@PathVariable int empId)
	{
		//receiver.log("main", "Update Employee Event  in Department Service");
		emp.setEmpId(empId);
		employeeService.updateEmpServ(emp);
		
		return "Employee Updated Successfully";
	}
	
	@DeleteMapping("/employee/deleteEmp/{empId}/{deptEmpFk}")
	public String deleteEmp(@PathVariable int empId,@PathVariable int deptEmpFk)
	{
		//receiver.log("main", "Delete Employee  Event  in Department Service");
		employeeService.deleteEmpFromDeptServ(empId, deptEmpFk);
		
		return "Employee Deleted Successfullly";
	}
	

}
