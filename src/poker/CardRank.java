package poker;

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
	

	public boolean isHigh(){
		return  this.equals(A) ||
				this.equals(K) ||
				this.equals(Q) ||
				this.equals(J);
	}
}
