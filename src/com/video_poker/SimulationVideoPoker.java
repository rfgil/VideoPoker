package com.video_poker;

import java.io.InputStream;

import com.card_game.exceptions.EmptyDeckException;
import com.card_game.exceptions.InvalidCardStringException;
import com.card_game.exceptions.NotEnoughBalanceException;
import com.video_poker.exceptions.BetNotSetException;
import com.video_poker.exceptions.IllegalCommandException;
import com.video_poker.exceptions.InvalidBetAmmountException;

public class SimulationVideoPoker extends VideoPoker {

	private int iterations;
	private int bet;
	
	private boolean debug;
	
	public SimulationVideoPoker(VideoPokerPlayer player, int iterations, int bet) {
		super(player);
		this.iterations = iterations;
		this.bet = bet;
		this.debug = false;
	}
	
	public SimulationVideoPoker(VideoPokerPlayer player, int iterations, int bet, InputStream card_file) throws InvalidCardStringException {
		super(player, card_file);
		this.iterations = iterations;
		this.bet = bet;
		this.debug = true;
	}

	@Override
	public void handAfterDeal(Hand hand) {
		if (debug) System.out.println("player's hand " + hand);
	}
	
	@Override
	public void handAfterHold(Hand hand) {
		if (debug){
			System.out.println("player's hand " + hand);
			System.out.println("RANK:  " + hand.getHandRank());
		}
	}
	
	@Override
	public void play() {
		for(int i=0; i<iterations; i++){
			try {
				System.out.println(i+1);

				super.bet(bet);
				super.deal();
				super.hold(super.advice());
				
			} catch (IllegalCommandException | InvalidBetAmmountException | BetNotSetException e) {
				// Nunca vai acontecer
				
			} catch ( EmptyDeckException e) {
				// So é possivel de acontecer em modo de debug
				System.out.println("deck has no cards left");
				break;
				 // System.exit(1);	// Não é possivel continuar execução
				
			} catch (NotEnoughBalanceException e){
				System.out.println("player has not enough balance to continue");
				break;
			}
		}
		
		super.player.printStatistics();
	}

}
