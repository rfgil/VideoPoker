package com.video_poker.hand_evaluator;

import java.util.ArrayList;
import java.util.List;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class Straight {
	
	//private List<Integer> gaps_list;
	private List<CardPos> list;
	
	//private StraightRank rank;
	
	private int high_card_count;
	private int gap_count;
	
	public Straight(List<CardPos> list, int[] gap_list, int start, int end) throws InvalidStraightException {	
		if (end - start < 2){ // Garante que a sequência tem pelo menos 3 elementos
			throw new InvalidStraightException();
		}
		
		this.list = new ArrayList<CardPos>(end - start + 1);
			
		high_card_count = 0;
		gap_count = 0;
		
		
		for(int i=start; i < end; i++){
			switch(gap_list[i]){
				case 0:	
					continue; // "Remove" (não insere) 1º carta duplicada (com o mesmo rank)
				case 2:
					gap_count ++; 
					// Não existe break intencionalmente para que seja adicionado à lista na mesma com gap_count == 2
				case 1:
					this.list.add(list.get(i));
					break;
					
				default:
					throw new InvalidStraightException();
			}
		}
		
		this.list.add(list.get(end));
		
		/*
		 * gap_count corresponde ao numero de gaps na própria sequência (ou parte dela)
		 * isto significa que gap_count = 0 => outside straight porque não existem gaps (espaços) entre as cartas desta sequencia
		 * no enunciado gaps corresponde às cartas em falta para completar a sequencia, que neste caso será dado por
		 * HAND_SIZE - tamanho da sequencia em causa + gap_count
		 */
	}
	
	public int getHighCardCount(){
		return high_card_count;
	}
	
	public int getGapCount(){
		return gap_count;
	}
	
	public StraightRank getStraightRank(){		
		switch(this.list.size()){
			case 5:
				return StraightRank.Straight;
			case 4:
				return StraightRank._4_to_straight;
			case 3:
				return StraightRank._3_to_straight;
			default:
				// não é possivel porque esta condição é garantida no construtor
				return null;
		}
	}
	
	public int getType(){
		int totalGaps = gap_count + Game.HAND_SIZE - this.list.size();
		
		if (high_card_count >= totalGaps &&
		    list.get(0).card.getRank() != CardRank.A){
			// Numero de cartas altas é superior ao numero gaps (na a sequência completa: totalGaps)  
			// E a sequência não tem um Low ace
			return 1;
			
		} else if (totalGaps == 1 || (totalGaps == 2 && high_card_count == 1) ) {
			return 2;
		
		} else if (this.list.size() == 3 &&
				   this.list.get(0).card.getRank() == CardRank._2 &&
				   this.list.get(1).card.getRank() == CardRank._3 &&
				   this.list.get(2).card.getRank() == CardRank._4){
			return 2;
			
		} else { //(totalGaps == 2 && high_card_count == 0){
			return 3;
		}
	}
	
	public boolean isRoyal(){
		return false;
	}
	
	public List<CardPos> getList(){
		return list;
	}
	
	/**
	 * Compares this straight with the specified straight object for order. Returns a negative integer, zero, or a positive integer as this straight is worse than, equal to, or better than the specified straight. 
	 * @param item Straight to compare with.
	 * @return
	 */
	public static int compare(Straight item1, Straight item2) {
		if (item1 == null && item2 == null){
			return 0;
		} else if(item1 == null){
			return 1;
		} else if(item2 == null){
			return -1;
		}
			
		int diff = item1.getStraightRank().compareTo(item2.getStraightRank());
		if (diff == 0){
			return item2.getType() - item1.getType();
		} else {
			return diff;
		}
	}
}
