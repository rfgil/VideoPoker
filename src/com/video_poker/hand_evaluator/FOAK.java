package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.Card;
import com.card_game.CardRank;
import com.video_poker.CardPos;

public class FOAK implements HandEvaluator{

	private CardRank card_rank;
	private int hit_counter;
	private List<CardPos> advice_hold_list;
	
	@Override
	public void addCard(CardPos card_pos) {
		if (card_pos.card.getRank().equals(card_rank)){
			hit_counter ++;
			advice_hold_list.add(card_pos);
			
		} else if (hit_counter != 4){ // As 4 cartas iguais vêm seguidas
			card_rank = card_pos.card.getRank();
			hit_counter = 1;
			
			advice_hold_list = new ArrayList<CardPos>(4);
			advice_hold_list.add(card_pos);
		}
	}

	@Override
	public AdviceRank getAdviceRank() {
		if (hit_counter == 4){
			return AdviceRank.FOAK;
		} else {
			advice_hold_list = new ArrayList<CardPos>();
			return AdviceRank.DiscardEverything;
		}
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (advice_hold_list.size() == 1){
			// caso a função getAdvicenk() não tenha sido chamada
			getAdviceRank();
		}
		
		return advice_hold_list;
	}

	@Override
	public HandRank getHandRank() {
		if (hit_counter == 4){
			CardRank foak_rank = advice_hold_list.get(0).card.getRank();
			if (foak_rank == CardRank.A){
				return HandRank.FourAces;
			} else if (foak_rank.compareTo(CardRank._5) < 0){
				return HandRank.Four2_4;
			} else {
				return HandRank.Four5_K;
			}
			
		} else {
			return HandRank.Nothing;
		}
	}
}
