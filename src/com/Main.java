package com;

import java.util.ArrayList;
import java.util.List;

import com.card_game.Card;
import com.card_game.CardRank;
import com.card_game.CardSuit;
import com.video_poker.CardPos;
import com.video_poker.CardPosComparator;
import com.video_poker.hand_evaluator.FOAK;
import com.video_poker.hand_evaluator.HandEvaluator;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Deck mydeck = new Deck();
		List<CardPos> hand = new ArrayList<CardPos>();	
		
		hand.add(new CardPos(new Card(CardRank.A, CardSuit.HEARTS), 0));
		hand.add(new CardPos(new Card(CardRank.A, CardSuit.DIAMONDS), 0));
		hand.add(new CardPos(new Card(CardRank.A, CardSuit.CLUBS), 0));
		hand.add(new CardPos(new Card(CardRank.A, CardSuit.SPADES), 0));
		hand.add(new CardPos(new Card(CardRank._6, CardSuit.HEARTS), 0));
		
		hand.sort(new CardPosComparator());
		
		HandEvaluator evaluator = new FOAK();
		
		for(CardPos card_pos : hand){
			evaluator.addCard(card_pos);
		}
		
		System.out.println(evaluator.getAdviceRank());
		System.out.println(evaluator.getAdviceHoldVector());
		System.out.println(evaluator.getHandRank());
	}

}