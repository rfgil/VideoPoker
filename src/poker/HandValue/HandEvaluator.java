package poker.HandValue;

import java.util.List;
import poker.CardPos;

public interface HandEvaluator {

	public void addCard(CardPos card_pos);
	public int adviceRank();
	public List<Integer> adviceHoldVector();
}
