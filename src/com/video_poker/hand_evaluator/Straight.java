package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class Straight implements HandEvaluator {
	
	private List<CardPos> hold_list;
	int n_gaps;
	int n_contiguous;
	
	int n_high_cards;
	
	int[] gaps;
	List<CardPos> cards;
	
	public Straight(){
		hold_list = new ArrayList<CardPos>();
		
		gaps = new int[Game.HAND_SIZE + 1]; // O mesmo às pode ser introduzido duas vezes (como carta baixa e alta)
		cards = new ArrayList<CardPos>(Game.HAND_SIZE +1 );
		
		n_gaps = 0;
		n_contiguous = 0;
		n_high_cards = 0;
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		int size = hold_list.size();
					
		if (size != 0){
			// Preenche posição anterior
			gaps[size-1] = card_pos.card.getRank().ordinal() - hold_list.get(size-1).card.getRank().ordinal();
		}
		
		hold_list.add(card_pos);
	}
	
	private void checkStraight(){
		int last_pos = hold_list.size() - 1;

		// Se a primeira carta for um às, ele pode ser considerado carta mais alta
		if (hold_list.get(0).card.getRank() == CardRank.A){
			// Transforma ÁS na carta mais alta e calcula diferença para com a ultima carta introduzida
			gaps[last_pos] = CardRank.A.ordinal() + CardRank.values().length - hold_list.get(last_pos).card.getRank().ordinal();

			// Introduz às como ultima carta
			hold_list.add(hold_list.get(0));
			gaps[hold_list.size() - 1] =  CardRank.values().length;
		} else {
			gaps[last_pos] = CardRank.values().length;
		}
		
		
		CardPos top_card;
		
		for (int i=last_pos; i>=0; i--){
			if (gaps[i] > 2){
				top_card = hold_list.get(i);
			}
			
			
			
		}
		
		
		for(int i = hold_list.size() -1; i>=0; i--){
			
		}
	}
	
	private void adjustHoldList(){
		while(hold_list.get(0).card.getRank() == CardRank.A && n_high_cards > 2){
			int last_pos =  hold_list.size() - 1; 
			// Transforma o ÁS na carta de valor mais alta e faz as verificações para 
			int diff = CardRank.A.ordinal() + CardRank.values().length + hold_list.get(last_pos).card.getRank().ordinal();
			
			if (diff == 1){
				n_contiguous ++;
				break;
			} else if (diff == 2){
				n_gaps  ++;
				break;
			} else {
				// Remove o ÁS da lista uma vez que não faz parte da sequência
				hold_list.remove(0);
			}
		}
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
