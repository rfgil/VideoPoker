package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class StraightEvaluator implements HandEvaluator {
	
	private List<CardPos> cards;
	private Straight straight;
	private int[] gaps;
	
	private boolean isStraightSet;
	
	public StraightEvaluator(){
		gaps = new int[Game.HAND_SIZE + 1]; // O mesmo às pode ser introduzido duas vezes (como carta baixa e alta)
		cards = new ArrayList<CardPos>(Game.HAND_SIZE + 1);
		isStraightSet = false;
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		int size = cards.size();
					
		if (size != 0){
			// Preenche posição anterior
			gaps[size-1] = card_pos.card.getRank().ordinal() - cards.get(size-1).card.getRank().ordinal();
		}
		
		cards.add(card_pos);
		isStraightSet = false;
	}
	
	private void setBestStraight(){
		int last_pos = cards.size() - 1;

		
		try {
			
		
		// Se a primeira carta for um às, ele pode ser considerado carta mais alta
		if (cards.get(0).card.getRank() == CardRank.A){
			// Transforma ÁS na carta mais alta e calcula diferença para com a ultima carta introduzida
			gaps[last_pos] = CardRank.A.ordinal() + CardRank.values().length - cards.get(last_pos).card.getRank().ordinal();

			// Introduz às como ultima carta
			cards.add(cards.get(0));
			gaps[cards.size() - 1] =  CardRank.values().length;
		} else {
			gaps[last_pos] = CardRank.values().length;
		}
		} catch (IndexOutOfBoundsException e){
			this.straight = null;
			isStraightSet = true;
			return;
		}
		
		
		List<Straight> straight_list = new ArrayList<Straight>(); 
		last_pos = 0;
		
		for (int i=0; i<cards.size(); i++){
			if (gaps[i] > 2){ // Procura um gap superior a 2, ou seja uma carta que quebre a possibilidade de sequência
				try {
					Straight straight = new Straight(cards, gaps, last_pos, i);
					straight_list.add(straight);
					last_pos = i + 1;
				} catch (InvalidStraightException e){}
			}
		}
		
		try {
			this.straight = straight_list.get(0);
			
			for(Straight list_straight : straight_list){
				if (Straight.compare(list_straight, this.straight) > 0){
					this.straight = list_straight;
				}
			}
		} catch (IndexOutOfBoundsException e){
			this.straight = null;
		}
		
		isStraightSet = true;
	}
	

	@Override
	public AdviceRank getAdviceRank() {
		if (!isStraightSet){
			setBestStraight();
		}
		
		if (straight == null){
			return AdviceRank.DiscardEverything;
			
		} else if (straight.getStraightRank() == StraightRank.Straight){
			return AdviceRank.Straight;
			
		} else if(straight.getStraightRank() == StraightRank._4_to_straight){
			if (straight.getGapCount() == 0){
				return AdviceRank._4_to_OutsideStraight;
				
			} else if (straight.getHighCardCount() == 3){
				return AdviceRank._4_to_InsideStraight_with_3_HighCards;
				
			} else if (straight.getHighCardCount() == 2){
				return AdviceRank._4_to_InsideStraight_with_2_HighCards;
				
			} else if (straight.getHighCardCount() == 1){
				return AdviceRank._4_to_InsideStraight_with_1_HighCard;
			
			} else if (straight.getHighCardCount() == 0){
				return AdviceRank._4_to_InsideStraight_with_0_HighCards;
			}
		}
		
		return AdviceRank.DiscardEverything;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (!isStraightSet){
			setBestStraight();
		}
		
		if (straight == null){
			return new ArrayList<CardPos>();
			
		} else if(straight.getStraightRank() == StraightRank.Straight  ||
			      straight.getStraightRank() == StraightRank._4_to_straight){
			return straight.getList();
		}
		
		return new ArrayList<CardPos>();
	}

	@Override
	public HandRank getHandRank() {
		if (!isStraightSet){
			setBestStraight();
		}
		
		if (straight != null && straight.getStraightRank() == StraightRank.Straight){
			return HandRank.Straight;
		}
		
		return HandRank.Nothing;
	}
	
	protected Straight getStraight(){
		if (!isStraightSet){
			setBestStraight();
		}
		
		return this.straight;
	}
	
	
}
