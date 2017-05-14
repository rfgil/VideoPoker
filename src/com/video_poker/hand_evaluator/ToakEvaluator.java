package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;

public class ToakEvaluator implements HandEvaluator {

	private List<CardPos> hold_list;
	
	public ToakEvaluator(){
		hold_list = new ArrayList<CardPos>(3);	
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		int size = hold_list.size();
		
		if (size != 3){			
			if (size == 0 || !hold_list.get(size-1).card.getRank().equals(card_pos.card.getRank())){
				hold_list = new ArrayList<CardPos>(3);
			}
			
			hold_list.add(card_pos);
		}
	}

	@Override
	public AdviceRank getAdviceRank() {
		if (hold_list.size() == 3){
			if (hold_list.get(0).card.getRank() == CardRank.A){
				return AdviceRank.TOAK_aces;
			} else {
				return AdviceRank.TOAK;
			}
		}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (hold_list.size() == 3){
			return hold_list;
		}
		
		return new ArrayList<CardPos>();
	}

	@Override
	public HandRank getHandRank() {
		if (hold_list.size() == 3){
			return HandRank.TOAK;
		}
		
		return HandRank.Nothing;
	}
}
