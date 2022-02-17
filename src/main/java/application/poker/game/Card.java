package application.poker.game;


public class Card {
	private int rank;
	private String suit;
	
	public int getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	


	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public String suitToString() {
//		switch(this.suit){
//		case("d"):
//			return "\u2666";
//		case("h"):
//			return "\u2665";
//		case("s"):
//			return "\u2660";
//		case("c"):
//			return "\u2663";
//		default:
//			return "!";
//		}
		return this.suit;
	}
	public String rankToString() {
//		if(this.rank>9) {
//			switch(this.rank) {
//			case(10):
//				return "T";
//			case(11):
//				return "J";
//			case(12):
//				return "Q";
//			case(13):
//				return "K";
//			case(14):
//				return "A";
//			default:
//				return"#";
//			}
//		}
//		else {
//			return String.valueOf(this.rank);
//		}
		
		return String.valueOf(this.rank);
	}
	
	public String toString(){
		//return "\u2320"+rankToString()+suitToString()+"\u2321";
		return rankToString()+suitToString();
	}
	
	
	public int compareTo(Card other) {
		if(this.rank > other.rank) {
			return 1;
		}
		else if(this.rank < other.rank) {
			return -1;
		}
		else{
			return 0;
		}
	}
	
	

	
}
