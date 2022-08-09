package com.muratk.crudoperationsmysql.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	@NotNull
	private String departmentName;
	@NotNull
	@Min(22)
	private String departmentAddress;
	@NotNull
	@NotBlank
	private String departmentCode;
	

}
