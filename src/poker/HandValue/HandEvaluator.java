package poker.HandValue;

import java.util.List;
import poker.CardPos;

public interface HandEvaluator {
	
	public void addCard(CardPos card_pos);
	
	public AdviceRank getAdviceRank();
	public List<CardPos> getAdviceHoldVector();
	
	public HandRank getHandRank();
}
