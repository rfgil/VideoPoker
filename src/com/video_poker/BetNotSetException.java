package com.video_poker;

public class BetNotSetException extends Exception {

	private static final long serialVersionUID = 1039223238794093585L;

	public BetNotSetException() {
		super("No bet was set");
	}
}
