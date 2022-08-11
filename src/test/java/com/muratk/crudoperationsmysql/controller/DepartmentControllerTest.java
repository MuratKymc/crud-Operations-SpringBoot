package com.muratk.crudoperationsmysql.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muratk.crudoperationsmysql.entity.Department;
import com.muratk.crudoperationsmysql.repository.DepartmentRepository;
import com.muratk.crudoperationsmysql.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
class DepartmentControllerTest {
	
	@Autowired(required = false)
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	@Autowired(required = false)
	private ObjectMapper objectMapper;
	
	@Test
	void saveDepartmentTest() throws Exception {
		Department department =  Department.builder()
				.departmentId(1L)
				.departmentName("depName")
				.departmentAddress("depAddress")
				.departmentCode("depCode")
				.build();
		mockMvc.perform(post("/departments").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(department)))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
	
	
	@Test
	void fetchDepartmentListTest() throws Exception {
		Department department =  Department.builder()
				.departmentId(1L)
				.departmentName("depName")
				.departmentAddress("depAddress")
				.departmentCode("depCode")
				.build();
		Department department2 =  Department.builder()
				.departmentId(2L)
				.departmentName("depName2")
				.departmentAddress("depAddress2")
				.departmentCode("depCode2")
				.build();
		List<Department> departments = new ArrayList<>();
		departments.add(department);
		departments.add(department2);
		
		when(departmentRepository.findAll()).thenReturn(departments);
		mockMvc.perform(get("/departments"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(departments.size()))
				.andDo(print());

	}
	
	@Test
	void getOneDepartmentTest() throws Exception {
		long id = 1L;
		Department department =  Department.builder()
				.departmentId(1L)
				.departmentName("depName")
				.departmentAddress("depAddress")
				.departmentCode("depCode")
				.build();
		when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
		mockMvc.perform(get("/departments/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentId").value(id))
				.andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()))
				.andExpect(jsonPath("$.departmentAddress").value(department.getDepartmentAddress()))
				.andExpect(jsonPath("$.departmentCode").value(department.getDepartmentCode()))
				.andDo(print());
	
	}
	
	@Test
	void updateDepartmentTest() throws Exception {
		long id = 1L;
		Department department =  Department.builder()
				.departmentId(1L)
				.departmentName("depName")
				.departmentAddress("depAddress")
				.departmentCode("depCode")
				.build();
		
		Department updatedDepartment =  Department.builder()
				.departmentId(2L)
				.departmentName("updatedName")
				.departmentAddress("updatedAddress")
				.departmentCode("updatedCode")
				.build();
		

		when(departmentRepository.findById(id)).thenReturn(Optional.of(department));
		when(departmentRepository.save(any(Department.class))).thenReturn(updatedDepartment);
		mockMvc.perform(put("/departments/{id}", id).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updatedDepartment)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.departmentName").value(updatedDepartment.getDepartmentName()))
				.andExpect(jsonPath("$.departmentAddress").value(updatedDepartment.getDepartmentAddress()))
				.andExpect(jsonPath("$.departmentCode").value(updatedDepartment.getDepartmentCode()))
				.andDo(print());
		
	}
	
	@Test
	void deleteDepartmentById() throws Exception {
		long id = 1L;
		doNothing().when(departmentRepository).deleteById(id);
		mockMvc.perform(delete("/departments/{id}", id))
				.andExpect(status().isNoContent())
				.andDo(print());
		
	}
	
	@Test
	void deleteAllDepartments() throws Exception {
		doNothing().when(departmentRepository).deleteAll();
		mockMvc.perform(delete("/departments"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}
}
