package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="Test_Employee")
public class Employee {
	@Id
	@GeneratedValue
	private Integer empId;
	private String ename;
	private String edept;
	private Double esal;
}
