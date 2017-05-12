package com.video_poker;

import com.card_game.NotEnoughBalanceException;
import com.card_game.Player;
import com.video_poker.hand_evaluator.HandRank;

public class VideoPokerPlayer extends Player{
	
	private Statistic[] stats;
	
	public VideoPokerPlayer() {
		super();
		stats = new Statistic[HandRank.values().length];
		
		for (HandRank rank : HandRank.values()){
			stats[rank.ordinal()] = new Statistic(rank);
		}
	}
	
	public void getPayout(HandRank hand_rank, int credits) throws InvalidBetAmmountException{
		stats[hand_rank.ordinal()].update(credits);
		int payout = hand_rank.getPayout(credits);
		
		try {
			this.credit(payout);
		} catch (NotEnoughBalanceException e){
			// Nunca vai acontecer porque InvalidBetAmmount é lançado caso o valor da aposta não seja válido
			// ou seja payout nunca será negativo e por isso é sempre feito credito e nunca débito
		}
	}


}
