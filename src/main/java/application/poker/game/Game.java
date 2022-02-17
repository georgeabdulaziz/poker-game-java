package application.poker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Game {
	Deck deck;
	ArrayList<Player> players;
	Hand table;
	Player winner;
	Hand bestHand;
	Stats tableStats;
	int bigBlind;
	int smallBlind;
	int indexOfButton;
	int indexOfBigBlind;
	int indexOfSmallBlind;
	final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	
	
	//TODO the game should not have any of these arguments the tables will take the BB and the SB
	public Game(ArrayList<Player> players, int rounds, int bigBlind, int smallBlind, int indexOfButton) {
		this.players = players;
		this.tableStats = new Stats();
		this.deck = new Deck();
		this.bigBlind = bigBlind;
		this.smallBlind = smallBlind;
		this.indexOfButton = indexOfButton;
		this.indexOfSmallBlind = this.indexOfButton + 1;
		this.indexOfBigBlind = this.indexOfSmallBlind + 1;
		
		//for(int i = 0 ; i < rounds; i++) {
//			if(i%500000==0) {
//				System.out.println(i);
//			}
			//gameStart();
//			this.indexOfButton = (this.indexOfButton + 1)%this.players.size();
//			this.indexOfSmallBlind = (this.indexOfButton + 1)%this.players.size();
//			this.indexOfBigBlind = (this.indexOfSmallBlind + 1)%this.players.size();
		//}
	}
	
	
	//one game round 
	public void gameStart() {
		this.indexOfButton = (this.indexOfButton + 1)%this.players.size();
		this.indexOfSmallBlind = (this.indexOfButton + 1)%this.players.size();
		this.indexOfBigBlind = (this.indexOfSmallBlind + 1)%this.players.size();
		
		//executorService.scheduleAtFixedRate(Game::yes, 10, 10, TimeUnit.SECONDS);
		this.table = new Hand();
		deck.shuffleDeck();
		for(int j=0; j< this.players.size(); j++) {
			this.players.get(j).clearHand();;
		}
		for(int i = 0; i < 2; i++) {
			for(int j=this.indexOfSmallBlind; j< this.indexOfSmallBlind+this.players.size(); j++) {
				Player player = this.players.get(j % this.players.size());
				Hand updatedHand = player.getHand();
				updatedHand.appendCard(this.deck.getCard());
				player.setHand(updatedHand);
			}
		}
		for(int b=0; b<5;b++) {
			if(b==0||b==3||b==4) {
				@SuppressWarnings("unused")
				Card burn = this.deck.getCard();
			}
			this.table.appendCard(this.deck.getCard());
		}
		
		Hand bestHand = this.players.get(0).getBestHand(this.table);
		Player bestPlayer = this.players.get(0);
		for(int j=1; j< this.players.size(); j++) {
			Hand hand = this.players.get(j).getBestHand(this.table);
			if(hand.compareTo(bestHand)==1) {
				bestHand = hand;
				bestPlayer = this.players.get(j);
			}
		}
		this.winner = bestPlayer;
		this.bestHand = bestHand;
		this.winner.addWins();
		//this.toStringObject = this.updateToString();
		
	}
	
	public static void yes() {
		System.out.println("Yes");
	}
	
	public List<String> toCards() {
		List<String> cards = new ArrayList<String>();
		cards.addAll(deck.toCards());		
		return cards;
	};
	
	
//	public List<String> getPlayerCards(){
//		List<String> cards = new ArrayList<String>();
//		
//	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("");
		result.append(this.deck.toString());
		result.append("\n");
		result.append("\n");
		for(int j=0; j< this.players.size(); j++) {
			Player x = this.players.get(j);
			result.append(x.getName()+": "+x.getHand().toString());
			result.append(" >>>>>\t"+x.getBestHand().toString());
			result.append(" "+x.getBestHand().getEvalResult());
			result.append("\n");
		}
		Collections.sort(this.bestHand.getHand(), new CardComparator());
		result.append("\n");
		result.append(table.toString());
		result.append("\n");
		result.append("\t\t\t The winner is: "+this.winner.getName());
		result.append("\t"+bestHand.getEvalResult()+" "+this.bestHand.toString());
		//System.out.println(table.toString());
		return result.toString();
	}
	
	public String reportToString() {
		StringBuilder result = new StringBuilder("");
		result.append("\n");
		for(int j=0; j< this.players.size(); j++) {
			Player x = this.players.get(j);
			result.append(x.getName()+": "+x.getStatsReport());
			result.append("\n\n");
		}
		return result.toString();
	}
	
	
	
}
