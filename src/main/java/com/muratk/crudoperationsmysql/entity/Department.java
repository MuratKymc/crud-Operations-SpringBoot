package com.muratk.crudoperationsmysql.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	@Column(name = "department_name", nullable = false)
	private String departmentName;
	
	@Column(name = "department_address", nullable = false)
	private String departmentAddress;
	
	@Column(name = "department_code", nullable = false)
	private String departmentCode;
	
}
