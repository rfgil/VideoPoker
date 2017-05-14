package com.card_game;

public enum CardRank {
	A,
    _2,
    _3,
    _4,
    _5,
    _6,
    _7,
    _8,
    _9,
    T,
    J,
    Q,
    K;
	
	public static int difference(CardRank rank1, CardRank rank2, boolean isRank1HighAce){
		if (isRank1HighAce){		
			if (difference(rank1, rank2) == 0){ // Se forem ambos um Ás a diferença continua a ser 0
				return 0;
			} else if (rank1.equals(A)){
				return difference(rank1, rank2) + CardRank.values().length;
				
			}// else if (rank2.equals(A)){
			//	return difference(rank1, rank2) - CardRank.values().length;
			//}
		}

		return difference(rank1, rank2);
	}
	
	public static int difference(CardRank rank1, CardRank rank2){
		return rank1.ordinal() - rank2.ordinal();
	}

	public boolean isHigh(){
		return  this.equals(A) ||
				this.equals(K) ||
				this.equals(Q) ||
				this.equals(J);
	}
}
