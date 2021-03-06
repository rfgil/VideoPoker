package com.video_poker.hand_evaluator;

public enum AdviceRank {
	 DiscardEverything,
	 _3_to_flush_with_0_HighCards,
	 _4_to_InsideStraight_with_0_HighCards,
	 Jack, Queen, King,
	 KT_suited,
	 Ace,
	 KQ_unsuited, KJ_unsuited,
	 _3_to_StraightFlush_type3,
	 QT_suited,
	 _3_to_flush_with_1_HighCard,
	 QJ_unsuited,
	 JT_suited,
	 KQJ_unsuited,
	 _4_to_InsideStraight_with_1_HighCard,
	 _3_to_StraightFlush_type2,
	 _4_to_InsideStraight_with_2_HighCards,
	 _2_suited_HighCards,
	 _3_to_flush_with_2_HighCards,
	 QJ_suited,
	 _4_to_InsideStraight_with_3_HighCards,
	 _3_to_StraightFlush_type1,
	 AKQJ_unsuited,
	 LowPair,
	 _4_to_OutsideStraight,
	 _3_to_RoyalFlush,
	 _4_to_flush,
	 HighPair,
	 TwoPair,
	 _4_to_StraightFlush,
	 TOAK,
	 Straight, Flush, FullHouse,
	 TOAK_aces,
	 _4_to_RoyalFlush,
	 StraightFlush, FOAK, ; 
	// Não há problema de dividir porque a ordem do rank é mantida (e não é possivel ter 2 destes simultaneamente)

}

/* ---------------------------------------------------
 *    ADVICE RANKS
 * ---------------------------------------------------
 *  1.  Straight flush, four of a kind, royal flush
 *  2.  4 to a royal flush
 *  3.  Three aces
 *  4.  Straight, flush, full house
 *  5.  Three of a kind (except aces)
 *  6.  4 to a straight flush
 *  7.  Two pair
 *  8.  High pair
 *  9.  4 to a flush
 * 10.  3 to a royal flush
 * 11.  4 to an outside straight
 * 12.  Low pair
 * 13.  AKQJ unsuited
 * 14.  3 to a straight flush (type 1)
 * 15.  4 to an inside straight with 3 high cards
 * 16.  QJ suited
 * 17.  3 to a flush with 2 high cards
 * 18.  2 suited high cards
 * 19.  4 to an inside straight with 2 high cards
 * 20.  3 to a straight flush (type 2)
 * 21.  4 to an inside straight with 1 high card
 * 22.  KQJ unsuited
 * 23.  JT suited
 * 24.  QJ unsuited
 * 25.  3 to a flush with 1 high card
 * 26.  QT suited
 * 27.  3 to a straight flush (type 3)
 * 28.  KQ, KJ unsuited
 * 29.  Ace
 * 30.  KT suited
 * 31.  Jack, Queen or King
 * 32.  4 to an inside straight with no high cards
 * 33.  3 to a flush with no high cards
 * 34.  Discard everything
 */