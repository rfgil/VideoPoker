package poker;

import java.util.Arrays;
import java.util.Random;

public class Deck {

	private int DECK_SIZE;
	
	Card[] deck;
	private int current_card;

	public Deck() {
		DECK_SIZE = CardRank.values().length * CardSuit.values().length;
		
		deck = new Card[DECK_SIZE];
		
		int i = 0;
		for(CardSuit suit: CardSuit.values()){
			for (CardRank rank: CardRank.values()){
				deck[i] = new Card(rank, suit);
				i++;
			}
		}
		
		//current_card = 0;
		shuffle();
	}
	
	public Card draw(){
		current_card ++;
		
		if (current_card < DECK_SIZE){
			return deck[current_card - 1];
			
		} else {
			// LANÇAR EXCEPTION!!
			// Caso se chegue ao fim do baralho este é baralhado e é devolvida a próxima carta
			shuffle();
			current_card = 1;
			return deck[0];
		}
	}
	
	public void shuffle(){
		// Fisher–Yates shuffle
		// https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
		
		Card aux;
		Random rand = new Random();
		
		current_card = 0;
		
		for(int i = DECK_SIZE - 1; i > 0; i--){ // for i from n−1 downto 1 do
			int rnd = rand.nextInt(i+1); // Random integer such that 0 ≤ rnd ≤ i
			
			aux = deck[i]; // Swap
			deck[i] = deck[rnd];
			deck[rnd] = aux;
		}
	}

	@Override
	public String toString() {
		return "Deck [deck=" + Arrays.toString(deck) + "]";
	}
}
