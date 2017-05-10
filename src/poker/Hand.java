package poker;

import poker.HandValue.*;

import java.util.List;
import java.util.ArrayList; 

public class Hand {
	
	private List<CardPos> cards;
	
	HandEvaluator[] evaluators;
	
	public Hand() {
		this.cards = new ArrayList<CardPos>(Game.HAND_SIZE);
		
		HandEvaluator[] evaluators = new HandEvaluator[8];
		
		evaluators[0] = new Straight();
		evaluators[1] = new FOAK();
		evaluators[2] = new FullHouse();
		evaluators[3] = new Flush();
		evaluators[4] = new TOAK();
		evaluators[5] = new TwoPair();
		evaluators[6] = new Pair();
		evaluators[7] = new HighCard();
	}
	
	public void addCard(int pos, Card card){
		cards.add(new CardPos(card, pos));
	}
	
	public void swapCard(int pos, Card newCard){
		// Procura pela carta na posição pretendida
		for(CardPos item: cards){
			if(item.pos == pos){
				item.card = newCard;
			}
		}
	}
	
	public void valueHand(){
		cards.sort(new CardPosComparator());
		
		for (CardPos card_pos : cards){
			for(HandValue evaluator: evaluators){
				evaluator.addCard(card_pos);
			}
		}
	}
	
	public void getAdvice(){
		
		
	}
}
