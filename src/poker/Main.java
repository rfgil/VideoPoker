package poker;

import java.util.ArrayList;
import poker.HandValue.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck mydeck = new Deck();
		
		HandEvaluator evaluator = new Flush();
		
		evaluator.addCard(new CardPos(new Card(CardRank.Q, CardSuit.DIAMONDS), 0));
		evaluator.addCard(new CardPos(new Card(CardRank.J, CardSuit.DIAMONDS), 0));
		evaluator.addCard(new CardPos(new Card(CardRank._7, CardSuit.DIAMONDS), 0));
		evaluator.addCard(new CardPos(new Card(CardRank._5, CardSuit.DIAMONDS), 0));
		evaluator.addCard(new CardPos(new Card(CardRank._4, CardSuit.DIAMONDS), 0));
		
		System.out.println(evaluator.getHandRank());
		
	}

}
