package application.poker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
//import java.util.Comparator;

public class Hand {
	private ArrayList<Integer> cardEval;
	public ArrayList<Integer> getCardEval() {
		return cardEval;
	}

	private String evalResult="";
	public String getEvalResult() {
		return evalResult;
	}

	private ArrayList<Card> hand;
	public ArrayList<Card> getHand() {
		return hand;
	}

	public Hand() {
		this.hand = new ArrayList<Card>();
		this.cardEval = new ArrayList<Integer>();
	}
	
	public void appendCard(Card card) {
		this.hand.add(card);
	}
	public void removeCard(Card card) {
		this.hand.remove(card);
	}

	
	public String toString() {
		StringBuilder result = new StringBuilder("");
		for(Card card: this.hand) {
			result.append(card.toString());
		}
		return result.toString();
	}
	
	public List<String> toCard() {
		List<String> res = new ArrayList<String>();
		for(Card card: this.hand) {
			res.add(card.toString());
		}
		return res;
	}
	
//	public ArrayList<Card> sortByCard(ArrayList<Card> sortMe){
//		Collections.sort(sortMe, cardSort); 
//		return sortMe;
//	}
//	
//	static Comparator<Card> cardSort = new Comparator<Card>() {
//		@Override
//		public int compare(Card o1, Card o2) {
//			return o1.compareTo(o2);
//		}
//	};
	
	public int compareTo(Hand other) {
		Boolean[] homeEval = this.evaluate();
		Boolean[] otherEval = other.evaluate();
		//System.out.println(Arrays.toString(homeEval));
		//System.out.println(Arrays.toString(otherEval));
		for(int i=0; i<homeEval.length; i++) {
			if(homeEval[i] && !otherEval[i]) {
				return 1;
			}
			else if(!homeEval[i] && otherEval[i]) {
				return -1;
			}
			else if(homeEval[i] && otherEval[i]) {
				ArrayList<Integer> homeCardEval = this.getCardEval();
				ArrayList<Integer> otherCardEval = other.getCardEval();
				//System.out.println(homeCardEval);
				//System.out.println(otherCardEval);
				for(int k=0; k<homeCardEval.size(); k++) {
					if(homeCardEval.get(k)>otherCardEval.get(k)) {
						return 1;
					}
					else if(homeCardEval.get(k)<otherCardEval.get(k)) {
						return -1;
					}
				}
				return 0;
			}
		}
		return 0;
	}
	
	
	
	public Boolean[] evaluate() {
//		if(this.hand.size()!=5) {
//			return false;
//		}
		this.cardEval = new ArrayList<Integer>();
		Boolean[] eval = new Boolean[9];
		for(int i =0; i<eval.length; i++) {
			eval[i]= false;
		}
		ArrayList<Card> copy = new ArrayList<Card>();
		for(Card card: this.hand) {
			copy.add(card);
		}
		Collections.sort(copy, new CardComparator());
		
		//checking 2 3 4 5 14
		//and straight
		int num = 2;

		int endNum = copy.get(4).getRank();
		boolean straight1 = true;

		boolean straight3 = true;
		
		//checking flush
		boolean flush = true;
		String lastSuit = copy.get(4).getSuit();
		
		//checking occurrences
		HashMap<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
		occurrences.put(endNum, 1);
		
		for(int i=0; i<4; i++) {
			int rank = copy.get(i).getRank();
			String suit = copy.get(i).getSuit();
			
			if(straight1 && rank !=num) {
				straight1=false;
			}
			num+=1;
			
			
			if(straight3 && rank+ (4-i) != endNum) {
				straight3=false;
			}
			
			if(flush && !suit.equals(lastSuit)) {
				flush = false;
			}
			
			if(occurrences.containsKey(rank)) {
				occurrences.put(rank, occurrences.get(rank)+1);
			}
			else {
				occurrences.put(rank, 1);
			}
			
		}
		if(straight3 || (endNum==14 && straight1)) {
			eval[4] = true; //straight
			this.evalResult = "straight";
			// we have to update the card Eval based on the max to min here
			for(int i=4; i>-1; i--) {
				int toAdd = copy.get(i).getRank();
				this.cardEval.add(toAdd);
			}
		}
		if(flush) {
			eval[3] = true; //flush
			this.evalResult = "flush";
			if(this.cardEval.size()==0){
				for(int i=4; i>-1; i--) {
					int toAdd = copy.get(i).getRank();
					this.cardEval.add(toAdd);
				}
			}
		}
		
		//System.out.println(Arrays.toString(eval));
		
		if(eval[3] && eval[4]) {
			eval[0]=true; //straight flush
			this.evalResult = "straightFlush";
		}
		
		else if(eval[3] == false && eval[4]==false) {
			// if there are only two ranks in the occurrences cards it is either 
			if(occurrences.size()==2) {//four of kind/ or full house
				if(occurrences.containsValue(4)) {
					eval[1] = true;		//four of kind
					this.evalResult = "fourOfKind";
					int first = 0;
					int second = 0;
					for(Entry<Integer, Integer> entry: occurrences.entrySet()) {
						// Placing the 4 of a kind card in the first position 
						if(entry.getValue()==4) {
							first =  entry.getKey();
						}
						if(entry.getValue()==1) {
							second =  entry.getKey();
						}
					}
					// If two hand are both four of a kind then the first card to be compared is the four of a kind card
					this.cardEval.add(first);
					this.cardEval.add(second);
				}
				else {
					eval[2] = true; //full house
					this.evalResult = "fullHouse";
					int first = 0;
					int second = 0;
					for(Entry<Integer, Integer> entry: occurrences.entrySet()) {
						if(entry.getValue()==3) { 
							first =  entry.getKey();
						}
						if(entry.getValue()==2) {
							second =  entry.getKey();
						}
					}
					this.cardEval.add(first);
					this.cardEval.add(second);
				}
			}
			else if(occurrences.size()==3) {//four of kind/ or full house
				if(occurrences.containsValue(3)) {
					eval[5] = true;	//three of kind
					this.evalResult = "threeOfKind";
					int first = 0;
					int second = 0;
					int third = 0;
					for(Entry<Integer, Integer> entry: occurrences.entrySet()) {
						if(entry.getValue()==3) {
							first = entry.getKey();
						}
						if(entry.getValue()==1) {
							if(second>0) {
								int toAdd = entry.getKey();
								if(toAdd>second) {
									int temp = second;
									second = toAdd;
									third = temp;
								}
								else {
									third = toAdd;									
								}
							}
							else {
								second = entry.getKey();								
							}
						}
					}
					this.cardEval.add(first);
					this.cardEval.add(second);
					this.cardEval.add(third);
				}
				else {
					eval[6] = true; //two pairs
					this.evalResult = "twoPairs";
					int first = 0;
					int second = 0;
					int third = 0;
					for(Entry<Integer, Integer> entry: occurrences.entrySet()) {
						if(entry.getValue()==1) {
							third = entry.getKey();
						}
						if(entry.getValue()==2) {
							if(first>0) {
								int toAdd = entry.getKey();
								if(toAdd>first) {
									int temp = first;
									first = toAdd;
									second = temp;
								}
								else {
									second = toAdd;									
								}
							}
							else {
								first = entry.getKey();								
							}
						}
					}
					this.cardEval.add(first);
					this.cardEval.add(second);
					this.cardEval.add(third);
				}
			}
			else if(occurrences.size()==4) {
				eval[7] = true;  //pair
				this.evalResult = "pair";
				for(Entry<Integer, Integer> entry: occurrences.entrySet()) {
					if(entry.getValue()==2) {
						this.cardEval.add(entry.getKey());
						break;
					}
				}
			
				for(int i=4; i>-1; i--) {
					if(this.cardEval.get(0)!=copy.get(i).getRank()) {
						int toAdd = copy.get(i).getRank();
						this.cardEval.add(toAdd);						
					}
				}
			}
			else {
				eval[8] = true;
				this.evalResult = "highCard";
				for(int i=4; i>-1; i--) {
					int toAdd = copy.get(i).getRank();
					this.cardEval.add(toAdd);
				}
			}
		}
		return eval;
	}
	
}
