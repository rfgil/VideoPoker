package com.video_poker;

import java.util.List;

import com.card_game.Card;
import com.card_game.Deck;
import com.card_game.exceptions.EmptyDeckException;
import com.video_poker.hand_evaluator.*;

import java.util.ArrayList;
import java.util.Collections; 

public class Hand {
	
	private List<CardPos> cards;
	
	private HandEvaluator[] evaluators;
	
	protected Hand() {
		this.cards = new ArrayList<CardPos>(Game.HAND_SIZE);
	}
	
	/**
	 * Inicia o vector de avaliadores
	 */
	private void renewEvaluators(){
		this.evaluators = new HandEvaluator[8];
		
		evaluators[0] = new FlushEvaluator();
		evaluators[1] = new FoakEvaluator();
		
		evaluators[2] = new PairEvaluator();
		evaluators[3] = new ToakEvaluator();
		evaluators[4] = new FullHouseEvaluator(evaluators[2], evaluators[3]);
		
		evaluators[5] = new HighCardEvaluator();
		evaluators[6] = new StraightEvaluator();
		evaluators[7] = new StraightFlushEvaluator();
	}
	
	/**
	 * Adiciona carta
	 * @param pos - posição
	 * @param card - carta a adicionar
	 */
	protected void addCard(int pos, Card card){
		cards.add(new CardPos(card, pos));
	}
	
	/**
	 * Troca as cartas que não são mantidas
	 * @param hold - vector de booleanos com as posições a manter
	 * @param deck - baralho
	 * @throws EmptyDeckException
	 */
	protected void swapCards(boolean[] hold, Deck deck) throws EmptyDeckException{
		// Procura pela carta na posiÃ§Ã£o pretendida
		for(CardPos item: cards){
			if(!hold[item.pos]){
				item.card = deck.draw();
			}
		}
	}
	
	/*
	 * Dá o conselho através do vector de avaliadores
	 */
	protected List<Integer> getAdvice(){
		renewEvaluators();
		cards.sort(new CardPosComparator());
		
		HandEvaluator selected_evaluator = evaluators[0];
		AdviceRank best_advice = AdviceRank.DiscardEverything;
		
		// Adiciona cartas a todos os avaliadores por ordem de rank
		for (CardPos card_pos : cards){
			for(HandEvaluator evaluator: evaluators){
				evaluator.addCard(card_pos);
			}
		}
		
		// Obtem o avaliador com melhor sugestÃ£o
		for(HandEvaluator evaluator: evaluators){
			AdviceRank evaluator_rank = evaluator.getAdviceRank();
			
			if (evaluator_rank.compareTo(best_advice) > 0){
				selected_evaluator = evaluator;
				best_advice = evaluator_rank;
			}
		}
			
		List<Integer> hold_vector = new ArrayList<Integer>(Game.HAND_SIZE);
		for(CardPos card_pos : selected_evaluator.getAdviceHoldVector()){
			hold_vector.add(card_pos.pos);
		}
		Collections.sort(hold_vector);
		return hold_vector;
	}
	
	/**
	 * Dá a classificação da mão de acordo com o vector de avaliadores
	 * @return
	 */
	protected HandRank getHandRank(){
		renewEvaluators();
		cards.sort(new CardPosComparator());
		
		HandRank best_rank = HandRank.Nothing;
		
		// Adiciona cartas a todos os avaliadores por ordem de rank
		for (CardPos card_pos : cards){
			for(HandEvaluator evaluator: evaluators){
				evaluator.addCard(card_pos);
			}
		}
		
		// Obtem o avaliador com melhor sugestÃ£o
		for(HandEvaluator evaluator: evaluators){
			if (evaluator.getHandRank().compareTo(best_rank) > 0){
				best_rank = evaluator.getHandRank();
			}
		}
		
		return best_rank;
	}

	@Override
	public String toString() {
		cards.sort(new CardPosComparator(true));
		
		String str = "";
		str += cards.get(0).toString();
		for (int i=1; i<cards.size(); i++){
			str += " " + cards.get(i);
		}
		
		return str;
	}
}
