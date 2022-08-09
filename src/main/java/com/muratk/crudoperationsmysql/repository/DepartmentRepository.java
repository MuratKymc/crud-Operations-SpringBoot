package com.muratk.crudoperationsmysql.repository;

import com.muratk.crudoperationsmysql.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDepartmentCode(String code);
	
	Department findByDepartmentId(Long id);
}
