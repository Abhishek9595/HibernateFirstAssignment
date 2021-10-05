package com.te.hibernate.assignmentone;

public class CustomException extends RuntimeException {

	public String message;

	public CustomException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
