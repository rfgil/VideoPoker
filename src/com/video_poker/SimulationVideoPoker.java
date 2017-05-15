package com.video_poker;

import java.io.InputStream;

import com.card_game.exceptions.EmptyDeckException;
import com.card_game.exceptions.InvalidCardStringException;
import com.card_game.exceptions.NotEnoughBalanceException;
import com.video_poker.exceptions.BetNotSetException;
import com.video_poker.exceptions.IllegalCommandException;
import com.video_poker.exceptions.InvalidBetAmmountException;
import com.video_poker.hand_evaluator.HandRank;

public class SimulationVideoPoker extends VideoPoker {

	private int iterations;
	private int bet;
	
	public SimulationVideoPoker(VideoPokerPlayer player, int iterations, int bet) {
		super(player);
		this.iterations = iterations;
		this.bet = bet;
	}
	
	public SimulationVideoPoker(VideoPokerPlayer player, int iterations, int bet, InputStream card_file) throws InvalidCardStringException {
		super(player, card_file);
		this.iterations = iterations;
		this.bet = bet;
	}

	@Override
	public void handAfterDeal(Hand hand) {
		//System.out.println("player's hand " + hand);
	}
	
	@Override
	public void handAfterHold(Hand hand) {
		// TODO Auto-generated method stub
		System.out.println("player's hand " + hand);
		System.out.println("RANK:  " + hand.getHandRank());
		/*
		if (hand.getHandRank() == HandRank.Nothing){
			System.out.println("player loses and his credit is " + this.player.getBalance());
		} else {
			System.out.println("player wins with " + hand.getHandRank() + " and his credit is " + this.player.getBalance());
		}
		*/
	}
	
	@Override
	public void play() {
		for(int i=0; i<iterations; i++){
			try {
				System.out.println(i+1);

				super.bet(bet);
				super.deal();
				super.hold(super.advice());
				
			} catch (IllegalCommandException | InvalidBetAmmountException | 
					 NotEnoughBalanceException | BetNotSetException | EmptyDeckException e) {
				System.out.println("an exception ocurred");
				e.printStackTrace();
				super.player.printStatistics();
				return;
			} 
			
		}
		
		super.player.printStatistics();
	}

}
