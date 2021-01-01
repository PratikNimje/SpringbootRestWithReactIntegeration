package com.app.appError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

	private String time;
	private int code;
	private String status;
	private String message;
	private String module;

}
