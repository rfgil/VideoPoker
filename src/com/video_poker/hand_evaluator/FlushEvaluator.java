package com.video_poker.hand_evaluator;

import java.util.List;

import com.card_game.CardRank;
import com.card_game.CardSuit;
import com.video_poker.CardPos;
import com.video_poker.Game;

import java.util.ArrayList;



public class FlushEvaluator implements HandEvaluator {
	private List< List<CardPos> > suits;
	private List<CardPos> advice_hold_list;	
	
	public FlushEvaluator(){
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
	
	@Override
	public AdviceRank getAdviceRank() {
		AdviceRank final_advice = AdviceRank.DiscardEverything;
		
		for(List<CardPos> suit_list : suits){
			// Avalia tamanho da lista com as cartas de cada naipe
			int size = suit_list.size();
			
			AdviceRank current_advice = AdviceRank.DiscardEverything;
			List<CardPos> current_hold_list = new ArrayList<CardPos>();	
			
			if (size <= 1){
				continue;
			} else if (size == 5){ // FLUSH
				advice_hold_list = suit_list;
				//advice_rank = 4;
				return AdviceRank.Flush; //Pode fazer return, não existe melhor resultado
				
			} else if(size == 4){ // 4 to flush
				advice_hold_list = suit_list;
				//advice_rank = 9;
				return AdviceRank._4_to_flush; //Pode fazer return, não existe melhor resultado
				
			} else {
				int high_card_count = 0;
				CardPos[] cards = new CardPos[CardRank.values().length]; // inicializado com null
				
				// Preenche histograma e contabiliza high cards
				for (CardPos card_pos : suit_list){
					// Só é possivel existir um rank de cada porque está a ser avaliado apenas um naipe
					cards[card_pos.card.getRank().ordinal()] = card_pos; 
					
					if (card_pos.card.getRank().isHigh()){
						high_card_count ++; 
					}
				}
				
				if (cards[CardRank.Q.ordinal()] != null && cards[CardRank.J.ordinal()] != null){ // QJ suited
					advice_hold_list = new ArrayList<CardPos>(2); // A lista suit_list pode conter 3 elementos neste caso, mas apenas se pretendem guardar 2
					advice_hold_list.add(cards[CardRank.Q.ordinal()]);
					advice_hold_list.add(cards[CardRank.J.ordinal()]);
					//advice_rank = 16;
					return AdviceRank.QJ_suited; // Não é possivel existir melhor
					
				} else if (size == 3 && high_card_count == 2){ // 3 to flush (with 2 high cards)
					current_hold_list = suit_list;
					//advice_rank = 17;
					current_advice = AdviceRank._3_to_flush_with_2_HighCards; // Não é possivel existir melhor
					
				} else if(size == 2 && high_card_count == 2){ // 2 suited high cards
					current_hold_list = suit_list;
					//advice_rank = 18;
					current_advice = AdviceRank._2_suited_HighCards;
					
				} else if (cards[CardRank.J.ordinal()] != null && cards[CardRank.T.ordinal()] != null) { // JT suited
					current_hold_list = new ArrayList<CardPos>(2); // A lista suit_list pode conter 3 elementos neste caso, mas apenas se pretendem guardar 2
					current_hold_list.add(cards[CardRank.J.ordinal()]);
					current_hold_list.add(cards[CardRank.T.ordinal()]);
					//advice_rank = 23;
					current_advice = AdviceRank.JT_suited;

				} else if(size == 3 && high_card_count == 1) { // 3 to a flush with 1 high card
					current_hold_list = suit_list;
					//advice_rank = 25; 
					current_advice =  AdviceRank._3_to_flush_with_1_HighCard; // Não é possivel existir melhor
				
				} else if (cards[CardRank.Q.ordinal()] != null && cards[CardRank.T.ordinal()] != null) { // QT suited
					current_hold_list = new ArrayList<CardPos>(2); // A lista suit_list pode conter 3 elementos neste caso, mas apenas se pretendem guardar 2
					current_hold_list.add(cards[CardRank.Q.ordinal()]);
					current_hold_list.add(cards[CardRank.T.ordinal()]);
					//advice_rank = 26;
					current_advice = AdviceRank.QT_suited;
				
				} else if(cards[CardRank.K.ordinal()] != null && cards[CardRank.T.ordinal()] != null) { // KT suited
					current_hold_list = new ArrayList<CardPos>(2); // A lista suit_list pode conter 3 elementos neste caso, mas apenas se pretendem guardar 2
					current_hold_list.add(cards[CardRank.K.ordinal()]);
					current_hold_list.add(cards[CardRank.T.ordinal()]);
					//advice_rank = 30;
					current_advice = AdviceRank.KT_suited;
				
				} else if(size == 3 && high_card_count == 0){ // 3 to a flush with no high card
					current_hold_list = suit_list;
					//advice_rank = 33; 
					current_advice = AdviceRank._3_to_flush_with_0_HighCards; // Não é possivel existir melhor
				}	
			}
			
			if (current_advice.compareTo(final_advice) > 0){
				// Se current_advice for melhor que final_advice 
				// (melhor implica mais pequeno uma vez que as melhores sugestões estão no topo)
				final_advice = current_advice;
				advice_hold_list = current_hold_list;
			}
		}
		
		if (final_advice == AdviceRank.DiscardEverything){
			// Define a variavel advice_hold_list caso se tenha verificado nenhuma condição para o flush
			advice_hold_list = new ArrayList<CardPos>();
		}
		
		return final_advice;
	}

	@Override
	public List<CardPos> getAdviceHoldVector() {
		if (advice_hold_list == null){
			// caso a função getAdvicenk() não tenha sido chamada
			getAdviceRank();
		}
		
		return advice_hold_list;
	}

	@Override
	public HandRank getHandRank() {
		for(List<CardPos> suit_list : suits){
			if (suit_list.size() == 5){
				//FLUSH
				return HandRank.Flush;
			}
		}
		
		return HandRank.Nothing;
	}
}

/*  4.  flush
 *  9.  4 to a flush
 * 16.  QJ suited
 * 17.  3 to a flush with 2 high cards
 * 18.  2 suited high cards
 * 23.  JT suited
 * 25.  3 to a flush with 1 high card
 * 26.  QT suited
 * 30.  KT suited
 * 33.  3 to a flush with no high cards
 * */ 
