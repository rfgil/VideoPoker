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
	
	@Override
    public String toString() {
		String str = super.toString();
		if (str.length() == 2){
			return str.charAt(1) + "";
		}
		return str;
	}
	
	/**
	 * Retorna a diferenca entre duas cartas	
	 * param rank1 primeira carta
	 * param rank2 segunda carta
	 * param isRank1HighAce booleano que indica se é um high ace
	 * return diferenca entre duas cartas
	 */	
	public static int difference(CardRank rank1, CardRank rank2, boolean isRank1HighAce){
		if (isRank1HighAce){		
			if (difference(rank1, rank2) == 0){ // Se forem ambos um Ã�s a diferenÃ§a continua a ser 0
				return 0;
			} else if (rank1.equals(A)){
				return difference(rank1, rank2) + CardRank.values().length;
				
			}// else if (rank2.equals(A)){
			//	return difference(rank1, rank2) - CardRank.values().length;
			//}
		}

		return difference(rank1, rank2);
	}
	
	/**
	 * Retorna a diferença em inteiro
	 * param rank1 primeira carta
	 * param rank2 segunda carta
	 * return diferenca
	 */
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
