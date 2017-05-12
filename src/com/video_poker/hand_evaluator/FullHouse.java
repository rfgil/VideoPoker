package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.video_poker.CardPos;

public class FullHouse implements HandEvaluator{
	
	private HandEvaluator pair;
	private HandEvaluator toak;
	
	public FullHouse(HandEvaluator pair, HandEvaluator toak){
		this.pair = pair;
		this.toak = toak;
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		return;
	}

	@Override
	public AdviceRank getAdviceRank() {
		if ((pair.getAdviceRank() == AdviceRank.HighPair || pair.getAdviceRank() == AdviceRank.LowPair) &&
		     toak.getHandRank() == HandRank.TOAK){
			return AdviceRank.FullHouse;
		}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		ArrayList<CardPos> hold_list = new ArrayList<CardPos>();
		
		if ((pair.getAdviceRank() == AdviceRank.HighPair || pair.getAdviceRank() == AdviceRank.LowPair) &&
			 toak.getHandRank() == HandRank.TOAK){
			hold_list.addAll(pair.getAdviceHoldVector());
			hold_list.addAll(toak.getAdviceHoldVector());
		}
		
		return hold_list;
	}

	@Override
	public HandRank getHandRank() {
		if ((pair.getAdviceRank() == AdviceRank.HighPair || pair.getAdviceRank() == AdviceRank.LowPair) &&
		     toak.getHandRank() == HandRank.TOAK){
			return HandRank.FullHouse;
		}
		
		return HandRank.Nothing;
	}
}
