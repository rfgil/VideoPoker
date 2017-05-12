package com.card_game;

public class Player {
	
	private int balance;
	
	public Player() {
	}
	
	public void credit(int value) throws NotEnoughBalance{
		if (value >= 0){
			balance += value;
		} else {
			// Fazer credito de um valor negativo corresponde a fazer debito do seu simetrico
			debit(-value);
		}
	}
	
	public void debit(int value) throws NotEnoughBalance {
		if (value < 0){
			// Fazer debito de um valor negativo corresponde a fazer credito do seu simetrico
			credit(-value);
			
		} else if (balance >= value){
			balance = balance - value;
			
		} else {
			// Exceção
			throw new NotEnoughBalance(value);
		}
	}
	
	public int getBalance(){
		return balance;
	}	
}
