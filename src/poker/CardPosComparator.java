package poker;

import java.util.Comparator;

public class CardPosComparator implements Comparator<CardPos>{

	@Override
	public int compare(CardPos arg0, CardPos arg1) {

		if (arg0.card.getRank().ordinal() == arg1.card.getRank().ordinal()){
			return 0;
		} else {
			return arg0.card.getRank().ordinal() < arg1.card.getRank().ordinal() ? -1 : 1;
		}
	}
}

