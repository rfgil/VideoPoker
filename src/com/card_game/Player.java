package com.card_game;

public class Player {
	
	private int balance;
	
	public Player() {
	}
	
	public void credit(int value){
		balance += value;
	}
	
	public void debit(int value){
		if (balance >= value){
			balance = balance - value;
		} else {
			// Exceção
		}
	}
	
	public int getBalance(){
		return balance;
	}	
}
