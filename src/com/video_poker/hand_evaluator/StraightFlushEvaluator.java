package com.video_poker.hand_evaluator;

import java.util.List;

import com.card_game.CardSuit;
import com.video_poker.CardPos;

public class StraightFlushEvaluator implements HandEvaluator {
	private StraightEvaluator[] suits;
	StraightEvaluator evaluator;
	
	
	public StraightFlushEvaluator() {
		suits = new StraightEvaluator[CardSuit.values().length];
		evaluator = null;
		
		for (CardSuit suit : CardSuit.values()){
			suits[suit.ordinal()] = new StraightEvaluator();
		}
	}

	@Override
	public void addCard(CardPos card_pos) {
		suits[card_pos.card.getSuit().ordinal()].addCard(card_pos);
	}
	
	private void setEvaluator(){
		evaluator = suits[0];
		for (StraightEvaluator eval : suits){
			if (Straight.compare(eval.getStraight(), evaluator.getStraight()) > 0){
				evaluator = eval;
			}
		}
	}

	@Override
	public AdviceRank getAdviceRank() {
		if (evaluator == null){
			setEvaluator();
		}
		
		try {
			if (evaluator.getStraight().getStraightRank() == StraightRank.Straight){
				return AdviceRank.StraightFlush;
			
			} else if (evaluator.getStraight().getStraightRank() == StraightRank._4_to_straight){
				if (evaluator.getStraight().isRoyal()){
					return AdviceRank._4_to_RoyalFlush;
				} else {
					return AdviceRank._4_to_StraightFlush;
				}
				
			} else if (evaluator.getStraight().getStraightRank() == StraightRank._3_to_straight){
				if (evaluator.getStraight().isRoyal()){
					return AdviceRank._3_to_RoyalFlush;
				} else {
					switch(evaluator.getStraight().getType()){
						case 1:
							return AdviceRank._3_to_StraightFlush_type1;
						case 2:
							return AdviceRank._3_to_StraightFlush_type2;
						case 3:
							return AdviceRank._3_to_StraightFlush_type3;
						default:
							// Não acontece nunca
							return AdviceRank.DiscardEverything;
					}
				}
			}
		
		}catch (NullPointerException e){}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (evaluator == null){
			setEvaluator();
		}
		
		return evaluator.getAdviceHoldVector();
	}

	@Override
	public HandRank getHandRank() {
		if (evaluator == null){
			setEvaluator();
		}
		
		try {
			if (evaluator.getStraight().getStraightRank() == StraightRank.Straight){
				return evaluator.getStraight().isRoyal() ? HandRank.RoyalFLush : HandRank.StraightFlush;
			}
		} catch (NullPointerException e){}
		
		return HandRank.Nothing;
	}

}
