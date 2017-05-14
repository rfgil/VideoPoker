package com.video_poker.hand_evaluator;

import java.util.ArrayList;

import com.card_game.CardRank;
import com.video_poker.CardPos;
import com.video_poker.Game;

public class StraightArrayList extends ArrayList<CardPos> {

	private static final long serialVersionUID = 8314110753397573874L;
	
	private int n_gaps;
	private int n_high_cards;
	private boolean isFull;


	public StraightArrayList() {
		super(Game.HAND_SIZE);
		n_gaps = 0;
		n_high_cards = 0;
		isFull = false;
	}
	
	@Override
	public boolean add(CardPos item){
		if (!isFull){ // Verifica se a lista já está cheia
			
			int diff = 1;
			
			// Calcula a diferença entre a ultima carta (caso esta não seja a primeira)
			if (this.size() > 0){
				diff = CardRank.difference(item.card.getRank(), this.get(this.size()-1).card.getRank(), true); // Considera o às carta alta, uma vez que já está pelo menos uma carta na lista
				
				if (diff == 0){
					// Não insere se a carta tiver o mesmo rank que a última
					return false;
				}
			}
			
			if (this.size() + n_gaps + diff <= Game.HAND_SIZE){
				// Só permite inserir na lista se ainda houver possibilidade de fazer straight com as cartas actuais
				n_gaps += diff - 1;
				
				if (item.card.getRank().isHigh() && !(item.card.getRank().equals(CardRank.A) && this.size() == 0) ){ 
					// Não considera low-ace como carta alta
					n_high_cards ++;
				}
				
				return super.add(item);
			} else {
				// Considera a lista cheia
				isFull = true;
				n_gaps = Game.HAND_SIZE - this.size();
			}
		}
		
		return false;
		/*
		 * gap_count corresponde ao numero de gaps na própria sequência (ou parte dela)
		 * isto significa que gap_count = 0 => outside straight porque não existem gaps (espaços) entre as cartas desta sequencia
		 * no enunciado gaps corresponde às cartas em falta para completar a sequencia, que neste caso será dado por
		 * HAND_SIZE - tamanho da sequencia em causa + gap_count
		 */
	}
	
	public boolean isPossibleStraight(){
		if (n_gaps > 2){
			return false;
		} else if (isFull && this.size() < 3){ 
			// Se a lista está considerada cheia e tem menos de 3 cartas não é possivel fazer straight com essas cartas
			return false;
		}
		
		return true;
	}
	
	protected boolean isFull(){
		return isFull;
	}
	
	public int getHighCardCount(){
		return n_high_cards;
	}
	
	public int getGapCount(){
		return n_gaps;
	}
	
	/*
	public StraightRank getStraightRank(){		
		switch(this.size()){
			case 5:
				return StraightRank.Straight;
			case 4:
				return StraightRank._4_to_straight;
			case 3:
				return StraightRank._3_to_straight;
			default:
				return StraightRank.Invalid;
		}
	}
	*/
	
	public int getType(){
		int totalGaps = n_gaps + Game.HAND_SIZE - this.size();
		
		if (this.size() == 3 &&
		    this.get(0).card.getRank() == CardRank._2 &&
		    this.get(1).card.getRank() == CardRank._3 &&
		    this.get(2).card.getRank() == CardRank._4){
			// Esta sequência em especifico é sempre do tipo 2 (não importa para esta classe se é ou não suited)
			return 2;
		
		} else if (n_high_cards >= totalGaps){
			// Numero de cartas altas é superior ou igual ao numero gaps (na a sequência completa: totalGaps)  (low ace não é carta alta!)
			return 1;
			
		} else if (totalGaps == 1 || (totalGaps == 2 && n_high_cards == 1) ) {
			return 2;
			
		} else { //(totalGaps == 2 && high_card_count == 0){
			return 3;
		}
	}
	
	public boolean isRoyal(){
		for(CardPos card_pos : this){
			if(!card_pos.card.getRank().isHigh() && !card_pos.card.getRank().equals(CardRank.T)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Compares this straight with the specified straight object for order. Returns a negative integer, zero, or a positive integer as this straight is worse than, equal to, or better than the specified straight. 
	 * @param item Straight to compare with.
	 * @return
	 */
	public static int compare(StraightArrayList item1, StraightArrayList item2) {
		if (item1 == null && item2 == null){
			return 0;
		} else if(item1 == null){
			return 1;
		} else if(item2 == null){
			return -1;
		}

		int diff = item1.size() - item2.size(); 
		if (diff == 0){
			// Não são necessárias mais condições porque listas com menos de 3 cartas não são consideradas sequencias
			// e o tamanho máximo da mão é 5
			return item2.getType() - item1.getType(); // Quanto maior o tipo, pior é item
		} else {
			return diff;
		}
	}
}
