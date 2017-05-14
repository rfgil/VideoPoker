package com.card_game;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.card_game.exceptions.EmptyDeckException;
import com.card_game.exceptions.InvalidCardStringException;

public class Deck {

	private List<Card> deck;
	private int current_card;
	private boolean isGenerated;

	public Deck() {
		isGenerated = true;
		
		deck = new ArrayList<Card>(CardRank.values().length * CardSuit.values().length);
		for(CardSuit suit: CardSuit.values()){
			for (CardRank rank: CardRank.values()){
				deck.add(new Card(rank, suit));
			}
		}
		
		shuffle();
	}
	
	public Deck(InputStream card_file) throws InvalidCardStringException {	
		isGenerated = false;
		current_card = 0;
		deck = new ArrayList<Card>();
		
		Scanner scanner = new Scanner(card_file);
		
		while(scanner.hasNext()){
			deck.add(new Card(scanner.next()));
		}
		
		scanner.close();
	}
	
	public Card draw() throws EmptyDeckException{		
		if (current_card < deck.size()){
			current_card ++;
			return deck.get(current_card - 1);
		} else {
			throw new EmptyDeckException();
		}
	}
	
	public void shuffle(){
		if (isGenerated){ // Só permite baralhar se o baralho foi gerado e não lido de um ficheiro
			// Fisher–Yates shuffle
			// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
			Card aux;
			Random rand = new Random();
			
			for(int i = deck.size() - 1; i > 0; i--){ // for i from n−1 downto 1 do
				int rnd = rand.nextInt(i+1); // Random integer such that 0 ≤ rnd ≤ i
				
				aux = deck.get(i); // Swap
				deck.set(i, deck.get(rnd));
				deck.set(rnd, aux);
			}
			
			current_card = 0;
		}
	}

	@Override
	public String toString() {
		String str = deck.get(0).toString();
		
		for (int i=1; i<deck.size(); i++){
			str += " " + deck.get(i);
		}

		return str;
	}
}
