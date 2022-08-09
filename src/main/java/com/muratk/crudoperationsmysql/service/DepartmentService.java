package com.muratk.crudoperationsmysql.service;

import com.muratk.crudoperationsmysql.entity.Department;

import java.util.List;

public interface DepartmentService {
	
	//Save operations
	Department saveDepartment(Department department);
	
	//Read operations
	List<Department> fetchDepartmentList();
	
	//Read one
	Department getOneDepartment(Long id);
	
	//Update operations
	Department updateDepartment(Department department, Long departmentId);
	
	//Delete operations
	void deleteDepartmentById(Long departmentId);
	
	
}
