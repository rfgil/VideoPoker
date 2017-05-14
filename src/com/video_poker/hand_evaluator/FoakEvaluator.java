package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;

public class FoakEvaluator implements HandEvaluator{

	private List<CardPos> hold_list;
	
	public FoakEvaluator(){
		hold_list = new ArrayList<CardPos>(4);	
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		int size = hold_list.size();
		
		if (size != 4){			
			if (size == 0 || !hold_list.get(size-1).card.getRank().equals(card_pos.card.getRank())){
				hold_list = new ArrayList<CardPos>(4);
			}
			
			hold_list.add(card_pos);
		}
	}

	@Override
	public AdviceRank getAdviceRank() {
		if (hold_list.size() == 4){
			return AdviceRank.FOAK;
		}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (hold_list.size() == 4){
			return hold_list;
		}
		
		return new ArrayList<CardPos>();
	}

	@Override
	public HandRank getHandRank() {
		if (hold_list.size() == 4){
			CardRank foak_rank = hold_list.get(0).card.getRank();
			
			if (foak_rank == CardRank.A){
				return HandRank.FourAces;
			} else if (foak_rank.compareTo(CardRank._5) < 0){
				return HandRank.Four2_4;
			} else {
				return HandRank.Four5_K;
			}
			
		}
		
		return HandRank.Nothing;
	}
}
