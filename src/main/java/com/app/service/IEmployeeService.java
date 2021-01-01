package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface IEmployeeService {

	public Integer saveEmployee(Employee employee);
	public List<Employee> getAllEmployee();
	public void deleteEmployee(Integer id);
	public Employee getOneEmployee(Integer id);
	public void  updateEmployee(Employee employee);


}
