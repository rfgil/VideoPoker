package com.card_game;

public class NotEnoughBalance extends Exception {

	private static final long serialVersionUID = 884137888870509880L;

	public NotEnoughBalance(int credits) {
		super("Player does not have enough balance to debit " + credits + " credits");
	}

}
