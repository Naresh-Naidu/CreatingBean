package com.creatingBean.CreatingBean.exception;

import lombok.Data;

@Data
public class ErrorMessage {

	private int status;
	private String error;
	private String message;
}
