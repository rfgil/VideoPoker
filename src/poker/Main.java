package poker;

import java.util.ArrayList;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck mydeck = new Deck();
		
		
		ArrayList<CardPos> cards = new ArrayList<CardPos>();
		
		System.out.println(mydeck);
		
		/*
		cards.add(mydeck.draw());
		cards.add(mydeck.draw());
		cards.add(mydeck.draw());
		cards.add(mydeck.draw());
		cards.add(mydeck.draw());
		*/
		
		System.out.println(cards);
		
		cards.sort(new CardPosComparator());
		
		System.out.println(cards);
		
	}

}
