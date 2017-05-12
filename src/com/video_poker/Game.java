package com.video_poker;

import java.util.ArrayList;
import java.util.List;

import com.card_game.Deck;

public class Game {

	public static final int HAND_SIZE = 5;
	
	private Deck deck;
	
	protected Hand hand;
	private List<Integer> hold_list;
	
	public Game(Deck deck) {
		this.deck = deck;
		this.hold_list = new ArrayList<Integer>(0); // Não faz hold de nenhuma carta por defeito
		
		hand = new Hand();
		for (int i=0; i < HAND_SIZE ; i++){
			hand.addCard(i, deck.draw());
		}
	}
	
	public void hold(List<Integer> hold_list){
		this.hold_list = hold_list;
	}
		
	public void draw(){
		for(int hold_pos : hold_list){
			hand.swapCard(hold_pos, deck.draw());
		}
	}
}
