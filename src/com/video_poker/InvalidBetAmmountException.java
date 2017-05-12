package com.video_poker;

public class InvalidBetAmmountException extends Exception {

	private static final long serialVersionUID = 2515264078023886624L;

	public InvalidBetAmmountException() {
		super("Invalid bet ammount");
	}
}
