package com.card_game.exceptions;

public class EmptyDeckException extends Exception {

	private static final long serialVersionUID = 6009350615261589155L;

	public EmptyDeckException() {
		super("Deck is empty");
	}
}
