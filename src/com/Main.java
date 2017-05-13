package com;

import java.util.ArrayList;
import java.util.List;

import com.card_game.Card;
import com.card_game.CardRank;
import com.card_game.CardSuit;
import com.video_poker.CardPos;
import com.video_poker.CardPosComparator;
import com.video_poker.hand_evaluator.HandEvaluator;
import com.video_poker.hand_evaluator.InvalidStraightException;
import com.video_poker.hand_evaluator.Straight;
import com.video_poker.hand_evaluator.StraightEvaluator;
import com.video_poker.hand_evaluator.StraightFlushEvaluator;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Deck mydeck = new Deck();
			
		/*
		VideoPokerPlayer player = new VideoPokerPlayer();
		
		try {
			player.credit(500);
		} catch (NotEnoughBalanceException e) {
		}
		
		InteractiveVideoPoker cena = new InteractiveVideoPoker(player, new Scanner(new InputStreamReader(System.in)));
		
		cena.play();
		*/		
		
		List<CardPos> hand = new ArrayList<CardPos>();	
		
		hand.add(new CardPos(new Card(CardRank.A, CardSuit.HEARTS), 0));
		hand.add(new CardPos(new Card(CardRank._2, CardSuit.HEARTS), 0));
		hand.add(new CardPos(new Card(CardRank._3, CardSuit.HEARTS), 0));
		hand.add(new CardPos(new Card(CardRank._4, CardSuit.HEARTS), 0));
		hand.add(new CardPos(new Card(CardRank._5, CardSuit.HEARTS), 0));
		
		hand.sort(new CardPosComparator());
		
		HandEvaluator evaluator = new StraightFlushEvaluator();
		
		for(CardPos card_pos : hand){
			evaluator.addCard(card_pos);
		}
		
		System.out.println(evaluator.getAdviceRank());
		System.out.println(evaluator.getAdviceHoldVector());
		System.out.println(evaluator.getHandRank());
	}

}
