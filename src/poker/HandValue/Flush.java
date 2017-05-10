package poker.HandValue;

import java.util.List;
import java.util.ArrayList;


import poker.CardPos;
import poker.CardRank;
import poker.CardSuit;
import poker.Game;



public class Flush implements HandEvaluator {
	/*  4. Flush
	 *  9. 4 to flush
	 * 17. 3 to flush (with 2 high cards)
	 * 18. 2 suited high card
	 * 23. JT suited
	 * 26. QT suited
	 * 30. KT suited
	 * 33. 3 to flush (with no high cards)
	 */ 
	private List< List<CardPos> > suits;
	
	private int advice_rank;
	private List<CardPos> selected_suit_list;
	
	
	public Flush(){
		suits = new ArrayList<List<CardPos>>(CardSuit.values().length);
		 
		for (int i=0; i<CardSuit.values().length; i++){
			suits.add(new ArrayList<CardPos>(Game.HAND_SIZE));
		}
	}
	
	@Override
	public void addCard(CardPos card_pos) {
		List<CardPos> suit_list = suits.get(card_pos.card.getSuit().ordinal());
		suit_list.add(card_pos);
	}
	
	private void checkSuitList(List<CardPos> suit_list){
		int high_card_count = 0;
		CardRank high_card_rank = CardRank._2; // Inicializa com o rank mais baixo	
		boolean hasTen = false;
		
		for (CardPos card_pos : suit_list){
			if (card_pos.card.getRank().isHigh()){
				high_card_count ++;
				high_card_rank = card_pos.card.getRank();
			}
			
			if (card_pos.card.getRank() == CardRank.T){
				hasTen = true;
			}
		}
		
		
	}
	
	private void getAdvice(){
		for(List<CardPos> suit_list : suits){
			int size;
			switch(size = suit_list.size()){
				case 5: // FLUSH
					advice_rank = 4;
					selected_suit_list = suit_list;
					return; //Pode fazer return, não existe melhor resultado
				
				case 4: // 4 to flush
					advice_rank = 9;
					selected_suit_list = suit_list;
					return; //Pode fazer return, não existe melhor resultado
					
				case 3: 
					
					
				case 2:
					
					
			}
		}
	}
	

	@Override
	public int adviceRank() {
		
		return 0;
	}

	@Override
	public List<Integer> adviceHoldVector() {
		return null;
	}
}
