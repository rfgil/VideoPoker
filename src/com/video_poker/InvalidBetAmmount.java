package com.video_poker;

public class InvalidBetAmmount extends Exception {

	private static final long serialVersionUID = 2515264078023886624L;

	public InvalidBetAmmount() {
		super("Invalid bet ammount");
	}
}
