package com.card_game;

public class Card {
	
	//protected static final int RANK_SIZE = 13;
	//protected static final int SUIT_SIZE = 4;
	
	private CardSuit suit;
	private CardRank rank;
	
	public Card(CardRank rank, CardSuit suit) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public CardSuit getSuit(){
		return suit;
	}
	
	public CardRank getRank(){
		return rank;
	}
	
	@Override
	public String toString() {
		return rank + "" + suit;
	}
}
