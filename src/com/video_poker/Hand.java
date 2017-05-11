package com.video_poker;

import poker.HandValue.*;

import java.util.List;

import com.card_game.Card;
import com.video_poker.hand_evaluator.AdviceRank;
import com.video_poker.hand_evaluator.FOAK;
import com.video_poker.hand_evaluator.Flush;
import com.video_poker.hand_evaluator.HandEvaluator;
import com.video_poker.hand_evaluator.HandRank;

import java.util.ArrayList; 

public class Hand {
	
	private List<CardPos> cards;
	
	private HandEvaluator[] evaluators;
	
	public Hand() {
		this.cards = new ArrayList<CardPos>(Game.HAND_SIZE);
	}
	
	private void renewEvaluators(){
		HandEvaluator[] evaluators = new HandEvaluator[7];
		
		//evaluators[0] = new Straight();
		evaluators[1] = new FOAK();
		//evaluators[2] = new FullHouse();
		evaluators[3] = new Flush();
		//evaluators[4] = new TOAK();
		//evaluators[5] = new Pair();
		//evaluators[6] = new HighCard();
	}
	
	protected void addCard(int pos, Card card){
		cards.add(new CardPos(card, pos));
	}
	
	protected void swapCard(int pos, Card newCard){
		// Procura pela carta na posição pretendida
		for(CardPos item: cards){
			if(item.pos == pos){
				item.card = newCard;
			}
		}
	}
	
	public List<CardPos> getAdvice(){
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
		
		// Obtem o avaliador com melhor sugestão
		for(HandEvaluator evaluator: evaluators){
			if (evaluator.getAdviceRank().compareTo(best_advice) < 0){
				selected_evaluator = evaluator;
			}
		}
		
		return selected_evaluator.getAdviceHoldVector();
	}
	
	public HandRank getHandRank(){
		renewEvaluators();
		cards.sort(new CardPosComparator());
		
		HandRank best_rank = HandRank.Nothing;
		
		// Adiciona cartas a todos os avaliadores por ordem de rank
		for (CardPos card_pos : cards){
			for(HandEvaluator evaluator: evaluators){
				evaluator.addCard(card_pos);
			}
		}
		
		// Obtem o avaliador com melhor sugestão
		for(HandEvaluator evaluator: evaluators){
			if (evaluator.getHandRank().compareTo(best_rank) < 0){
				best_rank = evaluator.getHandRank();
			}
		}
		
		return best_rank;
	}
}
