package com.video_poker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.card_game.NotEnoughBalanceException;
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
		System.out.println(hand);
	}

	@Override
	public void handAfterHold(Hand hand) {
		// TODO Auto-generated method stub
		System.out.println(hand);
		if (hand.getHandRank() == HandRank.Nothing){
			System.out.println("player loses and his credit is " + this.player.getBalance());
		} else {
			System.out.println("player wins with " + hand.getHandRank() + " and his credit is " + this.player.getBalance());
		}
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
					} catch (IllegalCommandException e1) {
						System.out.println("b: illegal command");
						
					} catch (InvalidBetAmmountException e){
						previous_bet = 5;
						
					} catch (NotEnoughBalanceException e){
						
					}
					break;
					
				case '$': // credit
					int value = str_scan.nextInt();
					
					try {
						this.credit(value);
					} catch (NotEnoughBalanceException e) {
	
					}
					
					System.out.println(this.player.getBalance());
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
					break;
				case 's': // statistics
					break;
				default:
					System.out.println("comando inválido");
					break;
			}
			
			str_scan.close();
		}
		
	}

}
