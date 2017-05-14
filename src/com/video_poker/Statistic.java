package com.video_poker;

import com.video_poker.exceptions.InvalidBetAmmountException;
import com.video_poker.hand_evaluator.HandRank;

public class Statistic {
	
	private HandRank hand_rank;
	private int occurrences; // Permite calcular N1..N10 e N11 (numero total de deals)
	
	private int invested_credits;
	private int gained_credits;
	
	public Statistic(HandRank hand_rank) {
		this.hand_rank = hand_rank;
		this.occurrences = 0;
		
		this.invested_credits = 0;
		this.gained_credits = 0;
	}
	
	public void update(int credits) throws InvalidBetAmmountException{
		this.occurrences ++;
		this.invested_credits += credits;
		this.gained_credits += hand_rank.getPayout(credits);
	}
	
	public int getBalance(){
		return gained_credits - invested_credits;
	}
	
	public int getOcurrences(){
		return occurrences;
	}
}