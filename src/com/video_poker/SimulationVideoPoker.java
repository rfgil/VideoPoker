package com.video_poker;

import com.card_game.exceptions.EmptyDeckException;
import com.card_game.exceptions.NotEnoughBalanceException;
import com.video_poker.exceptions.BetNotSetException;
import com.video_poker.exceptions.IllegalCommandException;
import com.video_poker.exceptions.InvalidBetAmmountException;

public class SimulationVideoPoker extends VideoPoker {

	private int iterations;
	private int bet;
	
	public SimulationVideoPoker(VideoPokerPlayer player, int iterations, int bet) {
		super(player);
		this.iterations = iterations;
		this.bet = bet;
	}

	@Override
	public void handAfterDeal(Hand hand) {
	}
	
	@Override
	public void handAfterHold(Hand hand) {
	}
	
	@Override
	public void play() {
		for(int i=0; i<iterations; i++){
			try {
				System.out.println(super.player.getBalance());
				super.bet(bet);
				super.deal();
				super.hold(super.advice());
				System.out.println(i);
				
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
