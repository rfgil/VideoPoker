package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class Straight implements HandEvaluator {
	
	private List<CardPos> hold_list;
	int[] gaps;
	
	public Straight(){
		hold_list = new ArrayList<CardPos>();
		gaps = new int[Game.HAND_SIZE];
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		int size = hold_list.size();
				
		if (size != 0){
			gaps[size-1] = card_pos.card.getRank().ordinal() - hold_list.get(size-1).card.getRank().ordinal();
			
			if (size == Game.HAND_SIZE - 1){
				if (hold_list.get(0).card.getRank() == CardRank.A){
					gaps[size] = CardRank.A.ordinal() + CardRank.values().length - card_pos.card.getRank().ordinal(); //Transforma o às em hight card e calcula valor do gap
				} else {
					gaps[size] = CardRank.values().length;
				}
			}
		}
		
		hold_list.add(card_pos);
	}

	@Override
	public AdviceRank getAdviceRank() {
		
		return null;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HandRank getHandRank() {
		// TODO Auto-generated method stub
		return null;
	}


}
