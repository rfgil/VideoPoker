package com.video_poker.exceptions;

public class IllegalCommandException extends Exception {
	
	private static final long serialVersionUID = 2260057147388144429L;

	public IllegalCommandException() {
		super("Illegal command");
	}
}
