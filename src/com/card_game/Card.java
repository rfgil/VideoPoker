package com.card_game;

import com.card_game.exceptions.InvalidCardStringException;

public class Card {
	
	//protected static final int RANK_SIZE = 13;
	//protected static final int SUIT_SIZE = 4;
	
	private CardSuit suit;
	private CardRank rank;
	
	public Card(CardRank rank, CardSuit suit) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(String str) throws InvalidCardStringException {
		try{
			this.suit = CardSuit.valueOf(str.charAt(1) + "");
			
			try {
				int pos = Integer.parseInt(str.charAt(0)+ "") - 1;
				this.rank = CardRank.values()[pos];
			} catch (NumberFormatException e){
				this.rank = CardRank.valueOf(str.charAt(0)+ "");
			}
			
		} catch (IllegalArgumentException e){
			throw new InvalidCardStringException(str);
		}
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
