package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class StraightEvaluator implements HandEvaluator {
		
	private List<StraightArrayList> straights;
	private StraightArrayList best_straight;
	private CardPos ace;
	
	public StraightEvaluator(){	
		straights = new ArrayList<StraightArrayList>();
		straights.add(new StraightArrayList());
		
		best_straight = null;
		ace = null;
	}
	
	private void insertCard(CardPos card_pos){
		for (StraightArrayList list : straights){
			list.add(card_pos);
			
			//if (!list.isPossibleStraight()){
			//	straights.remove(list);
			//}
		}
		
		if (straights.get(straights.size() -1).isFull()){
			// Cria nova possivel straight e adiciona a carta recebida (se existir pelo menos um gap na ultima lista disponivel)
			straights.add(new StraightArrayList());
			straights.get(straights.size() -1).add(card_pos);
		}
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		insertCard(card_pos);
		
		if (card_pos.card.getRank() == CardRank.A){
			ace = card_pos; // Evita que existam 2 ases
		}
		
		best_straight = null;
	}
		
	private void setBestStraight(){
		if (ace != null){
			insertCard(ace);
		}
		
		best_straight = straights.get(0);
		for(StraightArrayList list : straights){
	
			if (StraightArrayList.compare(list, best_straight) > 0){
				best_straight = list;
			}
		}
	}
	
	@Override
	public AdviceRank getAdviceRank() {
		if (best_straight == null){
			setBestStraight();
		}
		
		switch (best_straight.size()){
		case 5:
			return AdviceRank.Straight;
		case 4:
			if (best_straight.getGapCount() == 0){
				return AdviceRank._4_to_OutsideStraight;
				
			} else if (best_straight.getHighCardCount() == 3){ // Não é possivel ter mais que 3 high cards e inside straight
				return AdviceRank._4_to_InsideStraight_with_3_HighCards;
				
			} else if (best_straight.getHighCardCount() == 2){
				return AdviceRank._4_to_InsideStraight_with_2_HighCards;
				
			} else if (best_straight.getHighCardCount() == 1){
				return AdviceRank._4_to_InsideStraight_with_1_HighCard;
			
			} else { //if (best_straight.getHighCardCount() == 0){
				return AdviceRank._4_to_InsideStraight_with_0_HighCards;
			}
			
		default:
			return AdviceRank.DiscardEverything;
		}
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (best_straight == null){
			setBestStraight();
		}
		
		if (best_straight.size() < 4){ // Só há advices para 4_to_SomeStraight
			return new ArrayList<CardPos>();
		}
		
		return best_straight;
	}

	@Override
	public HandRank getHandRank() {
		if (best_straight == null){
			setBestStraight();
		}
		
		if (best_straight.size() == Game.HAND_SIZE){
			return HandRank.Straight;
		}
		
		return HandRank.Nothing;
	}
	
	protected StraightArrayList getStraight(){
		if (best_straight == null){
			setBestStraight();
		}
		
		return best_straight;
	}
}
