package com.video_poker;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.card_game.exceptions.EmptyDeckException;
import com.card_game.exceptions.InvalidCardStringException;
import com.card_game.exceptions.NotEnoughBalanceException;
import com.video_poker.exceptions.BetNotSetException;
import com.video_poker.exceptions.IllegalCommandException;
import com.video_poker.exceptions.InvalidBetAmmountException;
import com.video_poker.hand_evaluator.HandRank;

public abstract class CommandLineVideoPoker extends VideoPoker {

	private int previous_bet;
	
	public CommandLineVideoPoker(VideoPokerPlayer player) {
		super(player);
		previous_bet = 5;
	}
	
	public CommandLineVideoPoker(VideoPokerPlayer player, InputStream card_file) throws InvalidCardStringException {
		super(player, card_file);
		previous_bet = 5;
	}

	@Override
	public void handAfterDeal(Hand hand) {
		// TODO Auto-generated method stub
		System.out.println("player's hand " + hand);
	}

	@Override
	public void handAfterHold(Hand hand) {
		// TODO Auto-generated method stub
		System.out.println("player's hand " + hand);
		
		if (hand.getHandRank() == HandRank.Nothing){
			System.out.println("player loses and his credit is " + this.player.getBalance());
		} else {
			System.out.println("player wins with " + hand.getHandRank() + " and his credit is " + this.player.getBalance());
		}
	}
		
	@Override
	public void bet(int value){
		try {
			super.bet(previous_bet);
			System.out.println("player is betting " + previous_bet);
			
		} catch (IllegalCommandException e1) {
			System.out.println("b: illegal command");
			
		} catch (InvalidBetAmmountException e){
			System.out.println("b: illegal amount");
			previous_bet = 5;
			
		} catch (NotEnoughBalanceException e){
			System.out.println("player has not enough balance to bet " + previous_bet);
			System.out.println("player's credit is " + this.player.getBalance());
		}
	}
	
	@Override
	public void credit(int value){
		try {
			super.credit(value);
		} catch (NotEnoughBalanceException e) {
			System.out.println("player has not enough balance to debit " + (-value));
		}
		
		System.out.println("player's credit is " + this.player.getBalance());
	}
	
	@Override
	public void deal(){
		try {
			super.deal();
		} catch (IllegalCommandException e) {
			System.out.println("d: illegal command");
		} catch (BetNotSetException e){
			System.out.println("d: illegal command (bet was not set yet)");
		} catch (EmptyDeckException e) {
			System.out.println("deck has no cards left");
			System.exit(1);	// Não é possivel continuar execução
		}
	}
	
	@Override
	public void hold(List<Integer> hold_list){
		try {
			super.hold(hold_list);
		} catch (IllegalCommandException e) {
			System.out.println("h: illegal command");
		} catch (EmptyDeckException e) {
			System.out.println("deck has no cards left");
			System.exit(1);	// Não é possivel continuar execução
		}
	}
	
	/**
	 * Imprime o conselho relativo à jogada
	 */
	private void printAdvice(){
		List<Integer> list;
		String str;
		
		try {
			list = super.advice();
		} catch (IllegalCommandException e) {
			System.out.println("a: illegal command");
			return;
		}
		
		switch(list.size()){
			case 0:
				str = "player should discard all cards";
				break;
			case 1:
				str = "player should hold card " + (list.get(0) + 1);
				break;
			default:
				str = "player should hold cards " + (list.get(0) + 1);
				
				for (int i=1; i<list.size(); i++){
					str += " " + (list.get(i) + 1); 
				}
		}
		
		System.out.println(str);
	}
	
	private void printScannerInput(int value){
		System.out.println(" " + value);
	}
	
	private void printScannerInput(List<Integer> list){
		for (int i : list){
			System.out.print(" " + i);
		}
		
		System.out.println();
	}
	
	
	
	protected boolean commandLinePlay(char command, Scanner scanner) {
		return commandLinePlay(command, scanner, false);
	}
	
	/**
	 * Corre o jogo pelo terminal, chamando os diferentes m�todos consoante o command selecionado
	 * @param command - caracter que define o comando a ser implementado
	 * @param scanner - para leitura do teclado
	 * @param printScannerInput - booleano
	 * @return
	 */
	protected boolean commandLinePlay(char command, Scanner scanner, boolean printScannerInput) {
		switch(command){
			case 'b': // bet
				if (scanner.hasNextInt()){
					previous_bet = scanner.nextInt();
					if (printScannerInput) printScannerInput(previous_bet);
				} else {
					if (printScannerInput) System.out.println();
				}

				this.bet(previous_bet);
				
				break;
				
			case '$': // credit
				int value;
				
				if (scanner.hasNextInt()){
					value = scanner.nextInt();
					if (printScannerInput) printScannerInput(value);
				} else { 
					if (printScannerInput) System.out.println();
					value = this.player.getInitialBalance() - this.player.getBalance(); // Resets player balance if no value is set
				}
				this.credit(value);
				break;
				
			case 'd': // deal
				if (printScannerInput) System.out.println();
				this.deal();
				break;
				
			case 'h': // hold
				List<Integer> hold_list = new ArrayList<Integer>(5);
				int count = 0;
				
				while (count < Game.HAND_SIZE && scanner.hasNextInt()){
					int pos = scanner.nextInt() - 1; // Passa para indice 0
					if (pos >= 0 && pos < Game.HAND_SIZE){ // Averigua se pos está dentro dos limites 
						hold_list.add(pos);
					}
					count ++;
				}
				
				if (printScannerInput) printScannerInput(hold_list);
				this.hold(hold_list);
				break;
				
			case 'a': // advice
				if (printScannerInput) System.out.println();
				printAdvice();
				break;
				
			case 's': // statistics
				if (printScannerInput) System.out.println();
				this.player.printStatistics();
				break;
				
			case 'q':
				return false;
				
			default:
				if (printScannerInput) System.out.println();
				System.out.println(command + ": invalid command");
				break;
		}
		
		return true;
	}
	
	@Override
	public abstract void play();
}
