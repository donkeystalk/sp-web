package com.sp.exceptions;

public class DuplicateUsernameException extends RuntimeException {

	private static final long serialVersionUID = -1473963645894231053L;

	public DuplicateUsernameException(String msg)
	{
		super(msg);
	}
}
