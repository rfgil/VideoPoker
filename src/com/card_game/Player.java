package com.card_game;

import com.card_game.exceptions.NotEnoughBalanceException;

public class Player {
	
	private int balance;
	private int initialBalance;
	
	public Player(int initialBalance) {
		this.initialBalance = initialBalance;
		this.balance = initialBalance;
	}
	
	public void credit(int value) throws NotEnoughBalanceException{
		if (value >= 0){
			balance += value;
		} else {
			// Fazer credito de um valor negativo corresponde a fazer debito do seu simetrico
			debit(-value);
		}
	}
	
	public void debit(int value) throws NotEnoughBalanceException {
		if (value < 0){
			// Fazer debito de um valor negativo corresponde a fazer credito do seu simetrico
			credit(-value);
			
		} else if (balance >= value){
			balance = balance - value;
			
		} else {
			// Exceção
			throw new NotEnoughBalanceException(value);
		}
	}
	
	public int getBalance(){
		return balance;
	}
	
	public int getInitialBalance(){
		return initialBalance;
	}
}
