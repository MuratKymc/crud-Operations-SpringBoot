package com.muratk.crudoperationsmysql.repository;

import com.muratk.crudoperationsmysql.entity.Department;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void saveDepartmentTest(){
		
		Department department = Department
				.builder()
				.departmentName("TestDepartment")
				.departmentAddress("TestAddress")
				.departmentCode("TestCode")
				.build();
		
		departmentRepository.save(department);
		
		Assertions.assertThat(department.getDepartmentId()).isNotNull();
	}
	
	@Test
	@Order(2)
	void findByDepartmentIdTest() {
		Department department = departmentRepository.findByDepartmentId(5L);
		
		Assertions.assertThat(department.getDepartmentId()).isEqualTo(5L);
	}
	
	
	@Test
	@Order(3)
	public void getListOfDepartmentTest(){
		
		List<Department> departments = departmentRepository.findAll();
		
		Assertions.assertThat(departments.size()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateDepartmentTest(){
		Department department = departmentRepository.findByDepartmentId(2L);
		
		department.setDepartmentCode("NewTestCode");
		
		Department departmentUpdated = departmentRepository.save(department);
		
		Assertions.assertThat(departmentUpdated.getDepartmentCode()).isEqualTo("NewTestCode");
		
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteDepartmentTest(){
		
		Department department = departmentRepository.findByDepartmentId(2L);
		
		departmentRepository.delete(department);
		
		Department department1 = null;
		
		Optional<Department> optionalDepartment = Optional.ofNullable(departmentRepository.findByDepartmentCode("NewTestCode"));
		
		if (optionalDepartment.isPresent()){
			department1 = optionalDepartment.get();
		}
	Assertions.assertThat(department1).isNull();;
	
	}
	
	
	
}
