package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardSuit;
import com.video_poker.CardPos;
import com.video_poker.Game;

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
			if (StraightArrayList.compare(eval.getStraight(), evaluator.getStraight()) > 0){
				evaluator = eval;
			}
		}
	}

	@Override
	public AdviceRank getAdviceRank() {
		if (evaluator == null){
			setEvaluator();
		}
		
		switch(evaluator.getStraight().size()){
		case 5:
			return AdviceRank.StraightFlush;
		case 4:
			if (evaluator.getStraight().isRoyal()){
				return AdviceRank._4_to_RoyalFlush;
			} else {
				return AdviceRank._4_to_StraightFlush;
			}
		case 3:
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
		default:
			return AdviceRank.DiscardEverything;
		}
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (evaluator == null){
			setEvaluator();
		}
		
		if (evaluator.getStraight().size() < 3){
			return new ArrayList<CardPos>();
		}
		
		return evaluator.getStraight();
	}

	@Override
	public HandRank getHandRank() {
		if (evaluator == null){
			setEvaluator();
		}
		
		if (evaluator.getStraight().size() == Game.HAND_SIZE){
			return evaluator.getStraight().isRoyal() ? HandRank.RoyalFlush : HandRank.StraightFlush;
		}
		
		return HandRank.Nothing;
	}

}
