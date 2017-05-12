package com.video_poker;

import java.util.List;

import com.card_game.Deck;
import com.card_game.NotEnoughBalance;

public abstract class VideoPoker {
	
	public static final int MAX_BET = 5;
	
	private VideoPokerPlayer player;
	private Game game;
	private Deck deck;

	private boolean isBetSet;
	private int credits;
	
	public VideoPoker(VideoPokerPlayer player){
		this.player = player;
		this.deck = new Deck();
		this.game = null;
		this.isBetSet = false;
	}
	
	public void deal() throws IllegalCommandException, BetNotSetException {
		if (game != null){
			// Lançar exceção (deal so é permitido antes do inicio de um jogo)
			throw new IllegalCommandException();
		}
		
		if (!isBetSet){
			// Lançar exceção (o jogador ainda não apostou)
			throw new BetNotSetException();
		}
		
		deck.shuffle();
		game = new Game(deck);
		
		handAfterDeal(game.hand);
	}
	
	public void bet(int value) throws IllegalCommandException, InvalidBetAmmount, NotEnoughBalance {	
		if (game != null){ // bet só é permitido antes do inicio do jogo
			throw new IllegalCommandException();
		}
		
		if (value < 1 || value > MAX_BET){
			throw new InvalidBetAmmount();
		}
		
		if (isBetSet){
			// Altera o valor da aposta do jogador se já existir uma aposta	
			try {
				player.debit(value - this.credits); // Faz debito da diferença (ou crédito se dif < 0)
				this.credits = value;
				
			} catch (NotEnoughBalance e) {
				// Remove todas as apostas
				this.isBetSet = false;
				player.credit(this.credits); // Nunca lançará exceção porque só são permitidos valores positivos em credits
				
				throw e; // Devolve exceção para o utilizador
			}
			
		} else {
			player.debit(value); // Pode lançar exceção de falta de banca
			this.credits = value;
			this.isBetSet = true;
		}
	}
	
	public void credit(int value) throws NotEnoughBalance {
		this.player.credit(value);
	}
	
	public void hold(List<Integer> hold_list) throws IllegalCommandException {
		if (game == null){ // só é permitido fazer hold depois de iniciar o jogo
			throw new IllegalCommandException();
		}
		
		game.hold(hold_list);
		game.draw();
		
		try {
			player.getPayout(game.hand.getHandRank(), credits);
		} catch (InvalidBetAmmount e){
			// É garantido que  valor de bet estará correcto uma vez que essa confirmação é feita em bet
		}
		
		handAfterHold(game.hand);
		
		game = null; // Termina a ronda
		isBetSet  = false;
	}
	
	public List<CardPos> advice() throws IllegalCommandException {
		if (game == null){ // só é possivel obter um advice quando está um jogo a decorrer
			throw new IllegalCommandException();
		}
		
		return game.hand.getAdvice();
	}
	
	
	public abstract void handAfterDeal(Hand hand);
	public abstract void handAfterHold(Hand hand);
	public abstract void play();
}