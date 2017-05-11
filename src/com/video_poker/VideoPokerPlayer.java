package com.video_poker;

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


}
