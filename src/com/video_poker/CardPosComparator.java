package com.video_poker;

import java.util.Comparator;

public class CardPosComparator implements Comparator<CardPos>{
	
	private boolean sortByPos;
	
	public CardPosComparator(){
		this.sortByPos = false;
	}
	
	public CardPosComparator(boolean sortByPos){
		this.sortByPos = sortByPos;	
	}
	
	/**
	 * Compara a posição de duas cartas numa hand
	 * @param arg0 - primeira carta
	 * @param arg1 - segunda carta
	 * @return separação
	 */
	private int ComparePos(CardPos arg0, CardPos arg1){
		if (arg0.pos == arg1.pos){
			return 0;
		} else {
			return arg0.pos < arg1.pos ? -1 : 1;
		}
	}
	
	/**
	 * Compara o número/figura de uma carta
	 * @param arg0 - primeira carta
	 * @param arg1 - segunda carta
	 * @return separação
	 */
	private int CompareRank(CardPos arg0, CardPos arg1){
		if (arg0.card.getRank().ordinal() == arg1.card.getRank().ordinal()){
			return 0;
		} else {
			return arg0.card.getRank().ordinal() < arg1.card.getRank().ordinal() ? -1 : 1;
		}
	}
	
	/**
	 * Implementa a comparação, que pode ser ou por rank ou por posição
	 */
	@Override
	public int compare(CardPos arg0, CardPos arg1) {
		if (sortByPos){
			return ComparePos(arg0, arg1);
		} else {
			return CompareRank(arg0, arg1);
		}
	}
}

