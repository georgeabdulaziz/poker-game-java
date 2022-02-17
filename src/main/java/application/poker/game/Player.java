package application.poker.game;

import java.util.HashSet;

public class Player {
	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}



	public String getName() {
		return name;
	}
	private Hand hand;
	private String name;
	private Stats playerStats;
	private Hand bestHand;
	private Bank bank;
	
	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
		this.playerStats = new Stats();
		this.bestHand = new Hand();
		this.bank = new Bank();
	}
	//also the player later should have a stack (chips) name and more
	
	public void clearHand() {
		this.hand= new Hand();
	}
	
	public void addWins() {
		this.playerStats.updateNumberOfWins();
	}
	
	public String getStatsReport() {
		return this.playerStats.report();
	}
	
	public int getChips(){
		return this.bank.getChips();
	}

	public void setChips(int amount){
		this.bank.setChips(amount);
	}

	//finding the best hand out of the 7 cards the table cards and the player cards
	public Hand getBestHand(Hand table) {
		//System.out.println(this.getName());
		Card first = this.hand.getHand().get(0);
		Card second = this.hand.getHand().get(1);
		Hand bestHand = table;
			
//		bestHand.appendCard(first);
//		bestHand.appendCard(second);
//		for(int i = 0; i<3; i++) {
//			Card card = table.getHand().get(i);
//			bestHand.appendCard(card);
//		}
//		if(table.getHand().size()==3) {
//			return bestHand;
//		}
		//System.out.println("firstBestHad For "+this.getName()+" "+this.getHand().toString());
		for(int v = 0 ; v < 5; v++) {
			Hand hand = new Hand();
			hand.appendCard(first);
			for(int i = v; i<v+4; i++) {
				Card card = table.getHand().get(i%5);
				hand.appendCard(card);
			}
			//System.out.println(this.getName()+ "4 comparing bestHaan: "+bestHand.toString()+" with: "+hand.toString());
			if(hand.compareTo(bestHand)==1) {
				bestHand = hand;
			}
		}
		for(int v = 0 ; v < 5; v++) {
			Hand hand = new Hand();
			hand.appendCard(second);
			for(int i = v; i<v+4; i++) {
				Card card = table.getHand().get(i%5);
				hand.appendCard(card);
			}
			//System.out.println(this.getName()+ "44 comparing bestHaan: "+bestHand.toString()+" with: "+hand.toString());
			if(hand.compareTo(bestHand)==1) {
				bestHand = hand;
			}
		}
//		if(table.getHand().size()==4) {
//			return bestHand;
//		}
		
		// Appending two cards now 
		HashSet<String> tried = new HashSet<String>();
		for(int v = 0 ; v < 5; v++) {
			for(int i = v+1; i< v+5 ; i++) {
				Hand hand = new Hand();
				hand.appendCard(first);
				hand.appendCard(second);
				// To eliminate redundancy I use a hashSet and add the tires combination
				StringBuilder t = new StringBuilder(String.valueOf(Math.max(v, i%5)));
				t.append(String.valueOf(Math.min(v, i%5)));
				if(tried.contains(t.toString())) {
					continue;
				}
				else {
					tried.add(t.toString());
				}
				
				// v and i%5 are basically the positions of the added two cards 
				// so we are leaving a place for them 
				for(int g = 0; g < 5; g++) {
					if(g!=v && g!=(i%5)) {
						Card card = table.getHand().get(g);
						hand.appendCard(card);
					}
				}
				//System.out.println(this.getName()+ "5 comparing bestHand: "+bestHand.toString()+" with: "+hand.toString());
				if(hand.compareTo(bestHand)==1) {
					bestHand = hand;
				}
			}
		}
		this.playerStats.updateOdd(bestHand.getEvalResult(), false);
		this.bestHand = bestHand;
		return bestHand;
	}

	public Hand getBestHand() {
		return bestHand;
	}
	
}
