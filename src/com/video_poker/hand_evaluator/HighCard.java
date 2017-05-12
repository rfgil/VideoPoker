package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;

public class HighCard implements HandEvaluator{
	
	CardPos[] rank_histogram;
	private List<CardPos> hold_list;	
	
	public HighCard() {
		rank_histogram = new CardPos[CardRank.values().length]; // Inicializado a null
		hold_list = null;
	}

	@Override
	public void addCard(CardPos card_pos) {
		rank_histogram[card_pos.card.getRank().ordinal()] = card_pos;
	}

	@Override
	public AdviceRank getAdviceRank() {
		hold_list = new ArrayList<CardPos>();
		
		if (rank_histogram[CardRank.A.ordinal()] != null && 
			rank_histogram[CardRank.K.ordinal()] != null &&
			rank_histogram[CardRank.Q.ordinal()] != null &&
			rank_histogram[CardRank.J.ordinal()] != null){
			
			hold_list.add(rank_histogram[CardRank.A.ordinal()]);
			hold_list.add(rank_histogram[CardRank.K.ordinal()]);
			hold_list.add(rank_histogram[CardRank.Q.ordinal()]);
			hold_list.add(rank_histogram[CardRank.J.ordinal()]);
			
			return AdviceRank.AKQJ_unsuited;
		} 
		
		if (rank_histogram[CardRank.K.ordinal()] != null && 
			rank_histogram[CardRank.Q.ordinal()] != null &&
			rank_histogram[CardRank.J.ordinal()] != null){
			
			hold_list.add(rank_histogram[CardRank.K.ordinal()]);
			hold_list.add(rank_histogram[CardRank.Q.ordinal()]);
			hold_list.add(rank_histogram[CardRank.J.ordinal()]);
			
			return AdviceRank.KQJ_unsuited;
		}
		
		if (rank_histogram[CardRank.Q.ordinal()] != null && 
			rank_histogram[CardRank.J.ordinal()] != null){
			
			hold_list.add(rank_histogram[CardRank.Q.ordinal()]);
			hold_list.add(rank_histogram[CardRank.J.ordinal()]);
			
			return AdviceRank.QJ_unsuited;
		}
		
		if (rank_histogram[CardRank.K.ordinal()] != null && 
			rank_histogram[CardRank.Q.ordinal()] != null){
			
			hold_list.add(rank_histogram[CardRank.K.ordinal()]);
			hold_list.add(rank_histogram[CardRank.Q.ordinal()]);
			
			return AdviceRank.KQ_unsuited;
		}
		
		if (rank_histogram[CardRank.K.ordinal()] != null && 
			rank_histogram[CardRank.J.ordinal()] != null){
			
			hold_list.add(rank_histogram[CardRank.K.ordinal()]);
			hold_list.add(rank_histogram[CardRank.J.ordinal()]);
			
			return AdviceRank.KJ_unsuited;
		}
		
		if (rank_histogram[CardRank.A.ordinal()] != null){
			hold_list.add(rank_histogram[CardRank.A.ordinal()]);
			return AdviceRank.Ace;
		}
		
		if (rank_histogram[CardRank.K.ordinal()] != null){
			return AdviceRank.King;
		}
		
		if (rank_histogram[CardRank.Q.ordinal()] != null){
			hold_list.add(rank_histogram[CardRank.Q.ordinal()]);
			return AdviceRank.Queen;
		}
		
		if (rank_histogram[CardRank.J.ordinal()] != null){
			hold_list.add(rank_histogram[CardRank.J.ordinal()]);
			return AdviceRank.Jack;
		}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (hold_list == null){
			getAdviceRank();
		}
		
		return hold_list;
	}

	@Override
	public HandRank getHandRank() {
		return HandRank.Nothing;
	}

}