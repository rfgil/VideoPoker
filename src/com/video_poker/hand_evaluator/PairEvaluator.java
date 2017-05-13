package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.video_poker.CardPos;

public class PairEvaluator implements HandEvaluator {

	private List<CardPos> advice_hold_list;
	
	public PairEvaluator(){
		advice_hold_list = new ArrayList<CardPos>();
	}

	public void addCard(CardPos card_pos){
		int size = advice_hold_list.size();
		
		if(size % 2 == 0){ // size é par -> Tem 0, 1 ou 2 pares
			advice_hold_list.add(card_pos); // Adiciona nova carta
			
		} else {
			
			if (advice_hold_list.get(size-1).card.getRank().equals(card_pos.card.getRank())){
				advice_hold_list.add(card_pos); // Encontrado um par
			} else {
				advice_hold_list.set(size-1, card_pos); // Substitui carta única
			}
		}
	}
			
	public AdviceRank getAdviceRank(){
		int size = advice_hold_list.size();
		if (size % 2 != 0){	//Foi introduzido um item na lista sem par 
			advice_hold_list.remove(size - 1);
			size --;
		}
		
		if (size == 4){
			return AdviceRank.TwoPair;
			
		} else if (size == 2){
			if (advice_hold_list.get(0).card.getRank().isHigh()){
				return AdviceRank.HighPair;
			} else {
				return AdviceRank.LowPair;
			}
		}
				
		return AdviceRank.DiscardEverything;
	}
	
	public List<CardPos> getAdviceHoldVector(){
		int size = advice_hold_list.size();
		if (size % 2 != 0){	//Foi introduzido um item na lista sem par 
			advice_hold_list.remove(size - 1);
			size --;
		}
		
		return advice_hold_list;
	}
	
	public HandRank getHandRank(){
		int size = advice_hold_list.size();
		if (size % 2 != 0){	//Foi introduzido um item na lista sem par 
			advice_hold_list.remove(size - 1);
			size --;
		}
		
		if (size == 4){
			return HandRank.TwoPair;
			
		} else if (size == 2){
			if (advice_hold_list.get(0).card.getRank().isHigh()){
				return HandRank.JacksOrBetter;
			}
		}
		
		return HandRank.Nothing;
	}
	


}
