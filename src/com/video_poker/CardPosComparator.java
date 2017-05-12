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
	
	private int ComparePos(CardPos arg0, CardPos arg1){
		if (arg0.pos == arg1.pos){
			return 0;
		} else {
			return arg0.pos < arg1.pos ? -1 : 1;
		}
	}
	
	private int CompareRank(CardPos arg0, CardPos arg1){
		if (arg0.card.getRank().ordinal() == arg1.card.getRank().ordinal()){
			return 0;
		} else {
			return arg0.card.getRank().ordinal() < arg1.card.getRank().ordinal() ? -1 : 1;
		}
	}
	
	@Override
	public int compare(CardPos arg0, CardPos arg1) {
		if (sortByPos){
			return ComparePos(arg0, arg1);
		} else {
			return CompareRank(arg0, arg1);
		}
	}
}

