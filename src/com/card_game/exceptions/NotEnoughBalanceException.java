package com.card_game.exceptions;

public class NotEnoughBalanceException extends Exception {

	private static final long serialVersionUID = 884137888870509880L;

	public NotEnoughBalanceException(int credits) {
		super("Player does not have enough balance to debit " + credits + " credits");
	}

}
