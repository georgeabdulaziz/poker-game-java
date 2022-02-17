package application.poker.game;

import java.util.HashMap;
import java.util.Map.Entry;

public class Stats {
	private long highCard;
	private long straight;
	private long flush;
	private long pair;
	private long twoPairs;
	private long fullHouse;
	private long straightFlush;
	private long threeOfKind;
	private long fourOfKind;
	private long numberOfWins;
	private long numberOfRuns;
	
	public Stats() {
		this.highCard = 0;
		this.straight = 0;
		this.flush= 0;
		this.pair= 0;
		this.twoPairs= 0;
		this.fullHouse= 0;
		this.straightFlush= 0;
		this.threeOfKind= 0;
		this.fourOfKind= 0;
		this.numberOfWins=0;
		this.numberOfRuns=0;
	}
	
	public void updateOdd(String message, boolean multiple) {
		this.numberOfRuns+=1;
		switch(message) {
//		case("addRound"):
		case("highCard"):
			this.highCard+=1;
			break;
		case("pair"):
			this.pair+=1;
			break;
		case("twoPairs"):
			this.twoPairs+=1;
			if(multiple) {
				this.pair+=2;
			}
			break;
		case("threeOfKind"):
			this.threeOfKind+=1;
			if(multiple) {
				this.pair+=1;
			}
			break;
		case("straight"):
			this.straight+=1;
			break;
		case("flush"):
			this.flush+=1;
			break;
		case("fullHouse"):
			this.fullHouse+=1;
			if(multiple) {
				this.pair+=1;
				this.threeOfKind+=1;
			}
			break;
		case("straightFlush"):
			this.straightFlush+=1;
			if(multiple) {
				this.straight+=1;
				this.flush+=1;
			}
			break;
		case("fourOfKind"):
			this.fourOfKind+=1;
			if(multiple) {
				this.pair+=2;
				this.twoPairs+=1;
			}
			break;
		default:
		}
	}
	
	public void updateNumberOfWins() {
		this.numberOfWins+=1;
	}
	
	public String report() {
		StringBuilder res = new StringBuilder("");
		res.append("\n");
		res.append("High Card: "+this.highCard+"//"+this.numberOfRuns+" = "+(double)this.highCard/this.numberOfRuns);
		res.append("\n");
		res.append("Pair: "+this.pair+"//"+this.numberOfRuns+" = "+(double)this.pair/this.numberOfRuns);
		res.append("\n");
		res.append("Two Pairs: "+this.twoPairs+"//"+this.numberOfRuns+" = "+(double)this.twoPairs/this.numberOfRuns);
		res.append("\n");
		res.append("Three of Kind: "+this.threeOfKind+"//"+this.numberOfRuns+" = "+(double)this.threeOfKind/this.numberOfRuns);
		res.append("\n");
		res.append("Straight: "+this.straight+"//"+this.numberOfRuns+" = "+(double)this.straight/this.numberOfRuns);
		res.append("\n");
		res.append("Flush: "+this.flush+"//"+this.numberOfRuns+" = "+(double)this.flush/this.numberOfRuns);
		res.append("\n");
		res.append("Full House: "+this.fullHouse+"//"+this.numberOfRuns+" = "+(double)this.fullHouse/this.numberOfRuns);
		res.append("\n");
		res.append("Four of Kind: "+this.fourOfKind+"//"+this.numberOfRuns+" = "+(double)this.fourOfKind/this.numberOfRuns);
		res.append("\n");
		res.append("Straight Flush: "+this.straightFlush+"//"+this.numberOfRuns+" = "+(double)this.straightFlush/this.numberOfRuns);
//		for(Entry<String, Long> entry: this.playersOdds.entrySet()) {
//			res.append("\n");
//			res.append(entry.getKey()+" >> "+entry.getValue());
//		}
		res.append("\n");
		res.append("number of wins: "+this.numberOfWins);
		return res.toString();
	}
}
