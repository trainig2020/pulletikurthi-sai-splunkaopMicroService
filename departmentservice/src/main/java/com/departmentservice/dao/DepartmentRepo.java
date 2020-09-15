package com.departmentservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.departmentservice.model.Department;
@Repository
public interface DepartmentRepo extends CrudRepository<Department, Integer>{

	void deleteByDeptId(int deptId);

}
