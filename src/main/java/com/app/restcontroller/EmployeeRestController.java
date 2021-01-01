package com.app.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.EmployeeNotFoundException;
import com.app.model.Employee;
import com.app.service.IEmployeeService;
import com.app.util.EmployeeUtil;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {

	private static final Logger LOG =LoggerFactory.getLogger(EmployeeRestController.class);
	@Autowired
	private IEmployeeService service;


	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
		LOG.info("Entered into save Employee");

		ResponseEntity<String> resp=null;
		try {
			Integer id =service.saveEmployee(employee);
			resp= ResponseEntity.ok("Employee Saved"+id);
			LOG.info("Employee Saved Successfully with id {}",id);

		} catch (Exception e) {
			resp=new ResponseEntity<String>("Employee Not Found",HttpStatus.INTERNAL_SERVER_ERROR);
			LOG.error("Unable to saved Product {}",e.getMessage());
		}
		return resp;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllEmployee(){
		ResponseEntity<?> resp =null;
		try {
			List<Employee> list =	service.getAllEmployee();
			resp= new ResponseEntity<List<Employee>>(list,HttpStatus.OK);

		} catch (Exception e) {
			resp = new ResponseEntity<String>("Employee Not found",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable Integer id){

		LOG.info("Entered into get one employee");
		ResponseEntity<?> resp=null;
		try {

			Employee emp =	service.getOneEmployee(id);

			resp = new ResponseEntity<Employee>(emp,HttpStatus.OK);
			LOG.info("Employee One fetch with id {}",id);
		} 
		catch(EmployeeNotFoundException enfe) {
			throw enfe;
		}

		catch (Exception e) {
			e.printStackTrace();
			LOG.info("Employee Not Found with{}",id);
			resp = new ResponseEntity<String>("Unable to fetch Employee",HttpStatus.INTERNAL_SERVER_ERROR)	;
		}
		return resp;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		ResponseEntity<String> resp=null;
		try {
			service.deleteEmployee(id);
			resp = ResponseEntity.ok("successfully deleted");
		} 
		catch(EmployeeNotFoundException enfe) {
			throw enfe;
		}

		catch (Exception e) {
			resp= new ResponseEntity<String>("Unable to delete",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployee(
			@RequestBody Employee employee,
			@PathVariable Integer id
			){
		ResponseEntity<String> resp  =null;
		try {
			Employee emp =	service.getOneEmployee(id);
			EmployeeUtil.copyNonNullValues(emp, employee);
			service.updateEmployee(emp);
			resp= ResponseEntity.ok("Employee Updated");


		} 
		catch (EmployeeNotFoundException  enfe) {
			throw enfe;
		}
		catch (Exception e) {
			e.printStackTrace();
			resp= new ResponseEntity<String>("Employee not Updated",HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resp;

	}

}
