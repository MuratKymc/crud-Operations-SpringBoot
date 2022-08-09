package com.muratk.crudoperationsmysql.controller;


import com.muratk.crudoperationsmysql.entity.Department;
import com.muratk.crudoperationsmysql.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	
	//Save operation
	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody Department department){
		return departmentService.saveDepartment(department);
	}
	
	//Read operation
	@GetMapping("/departments")
	public List<Department> fetchDepartmentList(){
		return departmentService.fetchDepartmentList();
	}
	
	//Read one
	@GetMapping("/departments/{id}")
	public Department getOneDepartment(Long id){
		return departmentService.getOneDepartment(id);
	}
	
	//Update operation
	@PutMapping("/departments/{id}")
	public Department updateDepartment(@RequestBody Department department,
	                                   @PathVariable("id") Long departmentId){
		return departmentService.updateDepartment(department,departmentId);
	}

	//Delete operations
	@DeleteMapping("departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId){
		departmentService.deleteDepartmentById(departmentId);
		return "Delete Succesfully";
	}
	
}

