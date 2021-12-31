package com.abraham.mobilecommunicationplatformtest.exceptions;

public class BussinessException extends Exception{

	private static final long serialVersionUID = 1L;

	public BussinessException(String errorMessage) {
        super(errorMessage);
    }
}