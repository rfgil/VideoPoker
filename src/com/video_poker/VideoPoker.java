package com.video_poker;

import java.util.List;

import com.card_game.Deck;
import com.card_game.Player;

public abstract class VideoPoker {
	
	private Player player;
	private Game game;
	private Deck deck;
	
	VideoPoker(Player player){
		this.player = player;
		this.deck = new Deck();
		this.game = null;
	}
	
	protected void deal(){
		if (game == null){ // apenas permitido fazer deal no inicio de uma ronda
			deck.shuffle();
			game = new Game(deck);
			
			printHand(game.hand);
		} else {
			// Lançar exceção
		}
	}
	
	protected void bet(){
		if (game == null){
			
			
		} else {
			// Lançar exceção
		}
		
	}
	
	protected void credit(){
		
	}
	
	protected void hold(List<Integer> hold_vector){
		if (game != null) { // Apenas é permitido fazer hold depois iniciada a ronda
			for (int hold_pos : hold_vector){
				this.game.hold(hold_pos);
			}
			
			game.draw();
			
			printHand(game.hand);
			saveStats();
			
			
			
			
			game = null; // Termina a ronda
		} else {
			// Lançar exceção
		}
	}
	
	protected void advice(){
		
	}
	
	private void saveStats(){
		
	}
	
	
	public abstract void printHand(Hand hand);
	public abstract void printGameResult();
	public abstract void play();
}
