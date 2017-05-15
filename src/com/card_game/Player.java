package com.card_game;

import com.card_game.exceptions.NotEnoughBalanceException;

public class Player {
	
	private int balance;
	private int initialBalance;
	
	public Player(int initialBalance) {
		if (initialBalance < 0){ // Inicializa banca a 0 se um valor negativo for introduzido
			initialBalance = 0;
		}
		
		this.initialBalance = initialBalance;
		this.balance = initialBalance;
	}
	
	/**
	 * Faz crédito de um valor no balanço de um jogador
	 * @param value - valor a adicionar
	 * @throws NotEnoughBalanceException
	 */
	public void credit(int value) throws NotEnoughBalanceException{
		if (value >= 0){
			balance += value;
		} else {
			// Fazer credito de um valor negativo corresponde a fazer debito do seu simetrico
			debit(-value);
		}
	}
	
	/**
	 * Faz débito de um valor no balanço de um jogador
	 * @param value - valor a retirar
	 * @throws NotEnoughBalanceException
	 */
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
