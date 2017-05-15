package com.video_poker;

import com.card_game.Player;
import com.card_game.exceptions.NotEnoughBalanceException;
import com.video_poker.exceptions.InvalidBetAmmountException;
import com.video_poker.hand_evaluator.HandRank;

public class VideoPokerPlayer extends Player{
	
	private Statistic[] stats;
	
	public VideoPokerPlayer(int initialBalance) {
		super(initialBalance);
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
	
	/**
	 * Imprime as estat�sticas
	 */
	public void printStatistics(){
		String str = String.format("%-15s%15s\n", "Hand", "Nb");;	
		str += "-------------------------------\n";
		
		int total_ocorrences = 0;
		
		for (HandRank rank : HandRank.values()){
			total_ocorrences += stats[rank.ordinal()].getOcurrences();
			str += String.format("%-15s%15d\n", rank.toString(), stats[rank.ordinal()].getOcurrences());
			//str += rank + "\t" + stats[rank.ordinal()].getOcurrences() + "\n";
		}
		
		double gain = (( this.getBalance() - this.getInitialBalance() ) / (double) this.getInitialBalance()) * 100;
		
		str += "-------------------------------\n";
		str += String.format("%-15s%15d\n", "Total", total_ocorrences);
		str += "-------------------------------\n";
		str += String.format("%-15s(%4.2f%%) %5d\n", "Credit", gain, this.getBalance());
		//str += "-------------\nCredit\t" + this.getBalance() + " (" + gain + "%)";
		
		System.out.println(str);
	}
}
