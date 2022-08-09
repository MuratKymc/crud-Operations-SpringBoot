package com.muratk.crudoperationsmysql.service;

import com.muratk.crudoperationsmysql.entity.Department;
import com.muratk.crudoperationsmysql.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	//Save operations
	@Override
	public Department saveDepartment(Department department) {
		var deneme = departmentRepository.findByDepartmentCode(department.getDepartmentCode());
		
		if(deneme !=null)
		throw new RuntimeException();
		
		return departmentRepository.save(department);
	}
	
	
	//Read operations
	@Override
	public List<Department> fetchDepartmentList() {
		return (List<Department>) departmentRepository.findAll();
	}
	
	@Override
	public Department getOneDepartment(Long id) {
		return departmentRepository.findByDepartmentId(id);
	}
	
	//Update operations
	@Override
	public Department updateDepartment(Department department, Long departmentId) {
		
		Department depDB = departmentRepository.findById(departmentId).get();
		
		if (Objects.nonNull(department.getDepartmentName())
				&& !"".equalsIgnoreCase(department.getDepartmentName())){
			depDB.setDepartmentName(department.getDepartmentName());
		}
		if (Objects.nonNull(department.getDepartmentAddress())
				&& !"".equalsIgnoreCase(department.getDepartmentAddress())){
			depDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		if (Objects.nonNull(department.getDepartmentCode())
				&& !"".equalsIgnoreCase(department.getDepartmentCode())){
			depDB.setDepartmentCode(department.getDepartmentCode());
		}
		return departmentRepository.save(depDB);
		
	}
	
	//Delete operations
	@Override
	public void deleteDepartmentById(Long departmentId) {
		
		departmentRepository.deleteById(departmentId);
		
	}
}
