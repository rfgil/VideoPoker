package com.video_poker.hand_evaluator;

import com.video_poker.VideoPoker;
import com.video_poker.exceptions.InvalidBetAmmountException;

public enum HandRank {
	 Nothing(0),
	 JacksOrBetter(1),
	 TwoPair(1),
	 TOAK(3),
	 Straight(5),
	 Flush(7),
	 FullHouse(10),
	 Four5_K(50),
	 Four2_4(80),
	 FourAces(160),
	 StraightFlush(50),
	 RoyalFlush(250);
	
	private int return_value;
	HandRank(int return_value){
		this.return_value = return_value;
	}
	
	public int getPayout(int credits) throws InvalidBetAmmountException{
		if (credits < 1 || credits > VideoPoker.MAX_BET){
			throw new InvalidBetAmmountException();
		} 
		
		if (this.equals(RoyalFlush) && credits == VideoPoker.MAX_BET){ // Caso especial para o Royal Flush e aposta máxima
			//factor = 4000 / (250 * 5 credits) = 3.2
			return (int) (credits * return_value * 3.2);
		}
		
		return credits * return_value;
	}
}

/* ---------------------------------------------------
 *    PRIZE RANKS
 * ---------------------------------------------------
 *  1. Straight Flush
 *  2. Four Aces
 *  3. Four 2–4
 *  4. Four 5–K
 *  5. Full House
 *  6. Flush
 *  7. Straight
 *  8. Three of a Kind
 *  9. Two Pair
 * 10. Jacks or Better
 */
