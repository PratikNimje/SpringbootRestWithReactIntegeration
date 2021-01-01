package com.app.errorHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.appError.ErrorInfo;
import com.app.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class AppErrorHandler {


	@ExceptionHandler(EmployeeNotFoundException.class)
	public	ResponseEntity<String> EmployeeNotFoundEntity(EmployeeNotFoundException
			enfe){

		return new ResponseEntity<String>(enfe.getMessage(),HttpStatus.NOT_FOUND); 
		}
	/* 
	 * @ExceptionHandler(EmployeeNotFoundException.class) public
	 * ResponseEntity<ErrorInfo>
	 * handleEmployeeNotFoundEntity(EmployeeNotFoundException enfe){ return new
	 * ResponseEntity<>( new ErrorInfo( new Date().toString(), "Not Found", 404,
	 * enfe.getMessage(), "Employee module", HttpStatus.NOT_FOUND));
	 * 
	 * }
	 */
}
