package com.card_game.exceptions;

public class InvalidCardStringException extends Exception{

	private static final long serialVersionUID = -3854096797676040188L;

	public InvalidCardStringException(String str) {
		super("'" +str + "' does not represent a valid card");
	}

}
