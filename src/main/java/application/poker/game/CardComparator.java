package application.poker.game;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

	@Override
	public int compare(Card o1, Card o2) {
		// TODO Auto-generated method stub
		int res = o1.compareTo(o2);
		if (res == 0) {
			return o1.getSuit().compareTo(o2.getSuit());
		}
		return res;
	}
}
