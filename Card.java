

/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Card.java
 *
 * Description: Abstract Card class from which a card class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Card {
	
	enum Rank{ //Represents the ranks that a card can have
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}

	enum Suit{ //Represents the suits that a card can have
		HEARTS, CLUBS, DIAMONDS, SPADES;
	}
	
	protected Rank rank; //Represents the rank
	protected Suit suit; //Represents the suit
	
/**
 * This is the constructor that initializes the card the given rank and suit
 * @param r This is the rank of the card
 * @param s This is the suit of the card
 */
	public Card(Rank r, Suit s){
		//O(1)
		rank = r;
		suit = s;
	}

/**
 * Fetches the rank of the card
 * @return Returns the rank
 */
	public Rank getRank(){
		//O(1)
		return rank;
	}
	
/**
 * Fetches the suit of the card
 * @return Returns the suit of the card
 */
	public Suit getSuit(){
		//O(1)
		return suit;
	}
	
	abstract boolean equals(Card c);
    
    abstract int getPoints();
		
	@Override
	public abstract String toString();

}

