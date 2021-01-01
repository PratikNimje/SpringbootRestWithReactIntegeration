package com.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.repo.EmployeeRepository;
import com.app.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public Integer saveEmployee(Employee e) {
		Integer id = repo.save(e).getEmpId();
		return id;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee>list =repo.findAll();
		list.sort((e1,e2)->e1.getEmpId()-e2.getEmpId());
		return list;
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee emp =     getOneEmployee(id);
		repo.delete(emp);
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Optional<Employee> opt= repo.findById(id);

		Employee emp = opt.orElseThrow(
				()-> new EmployeeNotFoundException("Employee Not Exist")
				);

		/*
		Employee emp = null;
		if(opt.isPresent()) {
			emp = opt.get();
		} else {
			throw new EmployeeNotFoundException("Employee Not Exist");
		}*/
		return emp;
	}

	@Override
	public void updateEmployee(Employee employee) {
		repo.save(employee);

	}

}
