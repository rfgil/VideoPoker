package poker;

public class Game {

	public static final int HAND_SIZE = 5;
	
	private Deck deck;
	
	protected Hand hand;
	private boolean[] hold_vector;
	
	public Game(Deck deck) {
		this.deck = deck;
		
		hand = new Hand();
		hold_vector = new boolean[HAND_SIZE];
		
		for (int i=0; i < HAND_SIZE ; i++){
			hand.addCard(i, deck.draw());
			hold_vector[i] = false;
		}
	}
	
	public void hold(int pos) {
		if (pos > HAND_SIZE || pos < 0){
			// Lançar excepção
		}
		
		this.hold_vector[pos] = true;
	}
	
	public void draw(){
		for(int i=0; i<HAND_SIZE; i++){
			if (!hold_vector[i]){
				hand.swapCard(i, deck.draw());
			}
		}
	}
}
