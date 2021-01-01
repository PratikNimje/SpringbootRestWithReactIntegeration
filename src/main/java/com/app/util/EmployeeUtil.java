package com.app.util;

import com.app.model.Employee;

public interface EmployeeUtil {

	public static  void copyNonNullValues(Employee db,Employee request) {

		//copy non null values

		if(request.getEname()!=null)
			db.setEname(request.getEname());
		if(request.getEdept()!=null)
			db.setEdept(request.getEdept());
		if(request.getEsal()!=null)
			db.setEsal(request.getEsal());

	}

}
