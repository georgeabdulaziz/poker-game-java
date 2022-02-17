package application.poker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.security.SecureRandom;

//import org.uncommons.maths.random.AESCounterRNG;
//import org.uncommons.maths.random.MersenneTwisterRNG;




public class Deck {
	private Card[] deck;
	private ArrayList<Card> deckList;
	private int nextCard;
	
//	private AESCounterRNG v;
//	MersenneTwisterRNG b;
	

	
	
	public Card getCard() {
		//System.out.println(this.deck[this.nextCard]);
		this.nextCard+=1;
		return this.deck[this.nextCard-1];
	}
//	public Card getCard() {
//		//System.out.println(this.deck[this.nextCard]);
//		this.nextCard+=1;
//		return this.deckList.get(this.nextCard-1);
//	}

	
	public Deck() {
		//this.deck = generateDeck();
		//this.b = new MersenneTwisterRNG();
		this.deckList = generateDeckList();
		//this.deck = generateDeck();
		this.nextCard=0;
//		try {
//			this.v = new AESCounterRNG();
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
	
	
//	public Card[] getDeck() {
//		return this.deck;
//	}
	
	public ArrayList<Card> generateDeckList(){
		String[] suits = {"s","d","h","c"};
		ArrayList<Card> deck = new ArrayList<Card>();
		int suit = 0;
		for(int i = 0 ; i < 52; i++ ) {
			int myRank = (i%13)+2; // +2 is because the Ace is 14 here so I'm starting with 2 
			String mySuit = suits[suit];
			if(myRank==14) {
				suit+=1;
			}
			 deck.add(new Card(myRank, mySuit));
		}
		return deck;
	}


	public void shuffleDeck(){
		this.nextCard=0;
		SecureRandom rn = new SecureRandom();
		SecureRandom rng = new SecureRandom();
		Collections.shuffle(this.deckList, rng); //the deckList is shuffled every hand
		//and only initiated in the constructor
		ArrayList<Card> copyDeckList = new ArrayList<Card>();
		for(Card card: this.deckList) {
			copyDeckList.add(card);
		}
		this.deck = new Card[52];
		ArrayList<Integer> idxs = new ArrayList<Integer>();
		for(int i=0; i<52; i++) {
			idxs.add(i);
		}
		while(idxs.size()>0) {
			//random position for a random card from a randomly shuffled deck
			int ran = rn.nextInt(idxs.size());
			int ran1 = rng.nextInt(copyDeckList.size());
			int index = idxs.get(ran);
			this.deck[index] = copyDeckList.get(ran1);
			idxs.remove(ran);
			copyDeckList.remove(ran1);
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("");
		for(int i=0; i<this.deck.length; i++) {
			result.append(this.deck[i].toString());
		}
		return result.toString();
	}
	
	public List<String> toCards(){
		List<String> res = new ArrayList<String>();
		for(int i=0; i<this.deck.length; i++) {
			res.add(this.deck[i].toString());
		}
		return res;
	}
	
//	public String toString() {
//		StringBuilder result = new StringBuilder("");
//		for(int i=0; i<this.deckList.size(); i++) {
//			result.append(this.deckList.get(i).toString());
//		}
//		return result.toString();
//	}
	
}
