package com.video_poker.hand_evaluator;

import java.util.List;

import com.video_poker.CardPos;

public interface HandEvaluator {
	
	public void addCard(CardPos card_pos);
	
	public AdviceRank getAdviceRank();
	public List<CardPos> getAdviceHoldVector();
	
	public HandRank getHandRank();
}
