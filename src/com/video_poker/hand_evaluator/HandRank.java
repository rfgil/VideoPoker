package com.video_poker.hand_evaluator;

public enum HandRank {
	 RoyalFLush(250),
	 StraightFlush(50),
	 FourAces(160),
	 Four2_4(80),
	 Four5_K(50),
	 FullHouse(10),
	 Flush(7),
	 Straight(5),
	 TOAK(3),
	 TwoPair(1),
	 JacksOrBetter(1),
	 Nothing(0);
	
	private int return_value;
	HandRank(int return_value){
		this.return_value = return_value;
	}
	
	public int getPayout(int credits){
		if (credits < 1 || credits > 5){
			// Excepção
		} else if (this.equals(RoyalFLush) && credits == 5){
			return 4000;
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
