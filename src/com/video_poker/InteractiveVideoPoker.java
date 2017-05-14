package com.video_poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.card_game.NotEnoughBalanceException;
import com.video_poker.exceptions.BetNotSetException;
import com.video_poker.exceptions.IllegalCommandException;
import com.video_poker.exceptions.InvalidBetAmmountException;
import com.video_poker.hand_evaluator.HandRank;

public class InteractiveVideoPoker extends VideoPoker {
	
	private Scanner scanner;
	
	public InteractiveVideoPoker(VideoPokerPlayer player, Scanner scanner) {
		super(player);
		this.scanner = scanner;
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
	
	private void printAdvice(List<CardPos> list){
		String str;
		
		switch(list.size()){
			case 0:
				str = "player should discard all cards";
				break;
			case 1:
				str = "player should hold card " + (list.get(0).pos + 1);
				break;
			default:
				str = "player should hold cards " + (list.get(0).pos + 1);
				
				for (int i=1; i<list.size(); i++){
					str += " " + (list.get(i).pos + 1); 
				}
		}
		
		System.out.println(str);
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
		int previous_bet = 5; // Valor de aposta por defeito
		
		while(true){
			String str = scanner.nextLine();
			
			Scanner str_scan = new Scanner(str);
			char command = str_scan.next().charAt(0);
			
			switch(command){
				case 'b': // bet
					if (str_scan.hasNextInt()){
						previous_bet = str_scan.nextInt();
					}
					
					try {
						this.bet(previous_bet);
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
					break;
					
				case '$': // credit
					int value;
					if (str_scan.hasNextInt()){
						value = str_scan.nextInt();
						if (value < 0){
							value = 0;
						}
					} else {
						value = this.player.getInitialBalance() - this.player.getBalance();
					}
					
					try {
						this.credit(value);
					} catch (NotEnoughBalanceException e) {}
					
					System.out.println("player's credit is " + this.player.getBalance());
					break;
					
				case 'd': // deal
					try {
						this.deal();
					} catch (IllegalCommandException e) {
						System.out.println("d: illegal command");
					} catch (BetNotSetException e){
						System.out.println("d: illegal command (bet was not set yet)");
					}
					break;
					
				case 'h': // hold
					List<Integer> hold_list = new ArrayList<Integer>(5);
					int count = 0;
					
					while (count < Game.HAND_SIZE && str_scan.hasNextInt()){
						int pos = str_scan.nextInt() - 1; // Passa para indice 0
						if (pos >= 0 && pos < Game.HAND_SIZE){ // Averigua se pos está dentro dos limites 
							hold_list.add(pos);
						}
						count ++;
					}
					
					try {
						this.hold(hold_list);
					} catch (IllegalCommandException e) {
						System.out.println("h: illegal command");
					}
					break;
					
				case 'a': // advice
					try {
						printAdvice(this.advice());
					} catch (IllegalCommandException e) {
						System.out.println("a: illegal command");
					}
					break;
					
				case 's': // statistics
					this.player.printStatistics();
					break;
					
				default:
					System.out.println(command + ": invalid command");
					break;
			}
			
			str_scan.close();
		}
		
	}

}
