package com.video_poker;

import com.card_game.Card;

public class CardPos {
	
	public Card card;
	public int pos;
	
	public CardPos(Card card, int pos) {
		// TODO Auto-generated constructor stub
		this.card = card;
		this.pos = pos;
	}

	@Override
	public String toString() {
		return card.toString();
	}
}
