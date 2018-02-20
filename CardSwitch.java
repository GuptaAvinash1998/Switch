
/**************************************************************************
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: CardSwitch.java
 *
 * Description: This class is a child class from the abstract class Card. Here we define the card methods. A card has two things: A suit and a Value or rank
 * 
 ***************************************************************************/

public class CardSwitch extends Card{

/**
 * This is the constructor of the card where we define the rank and the suit
 * @param r This is the rank of the card
 * @param s This is the suit of the card
 */
	public CardSwitch(Rank r, Suit s){
		//O(1)
		super(r,s); //Since the class extends Card, Card is the super class and already has the same constructor
	}

/**
 * This method checks whether two cards are the same or not
 * @param anotherCard This is the card that we are checking is the same or not
 * @return returns a boolean value either true or false based on whether the cards are the same or not
 */
	@Override
	public boolean equals(Card anotherCard){
		// checks if two cards equals and returns a boolean
		//O(1)
		if(anotherCard.getRank().equals(this.getRank()) && anotherCard.getSuit().equals(this.getSuit())){
			return true;
		}else {
			return false;
		}
	}

/**
 * This method gets the rank of the card and based on the rank, the points will be assigned
 * @return returns the value of the rank
 */
	@Override
    public int getPoints(){
		//The super class has a method called getRank that will fetch the rank of the card from the super class and compares it with the enum of defined Ranks
		//O(1)
		if(super.getRank() == Rank.ACE) {
			return 1;
		}
		
		if(super.getRank() == Rank.TWO) {
			return 2;
		}
		
		if(super.getRank() == Rank.THREE) {
			return 3;
		}
		
		if(super.getRank() == Rank.FOUR) {
			return 4;
		}
		
		if(super.getRank() == Rank.FIVE) {
			return 5;
		}
		
		if(super.getRank() == Rank.SIX) {
			return 6;
		}
		
		if(super.getRank() == Rank.SEVEN) {
			return 7;
		}
		
		if(super.getRank() == Rank.EIGHT) {
			return 8;
		}
		
		if(super.getRank() == Rank.NINE) {
			return 9;
		}
		
		if(super.getRank() == Rank.TEN || super.getRank() == Rank.JACK || super.getRank() == Rank.QUEEN || super.getRank() == Rank.KING){
			return 10;
		}else {
			throw new RuntimeException(); //If the rank does not match any of the values, then it will throw an exception
		}
    }
	
/**
 * This method returns the value of the card in a specific format
 * @return returns the format
 */
	@Override
	public String toString(){
		// convert card to string consisting of as "(rank,suit)"
		//O(1)
		return "(" + super.getRank() + "," + super.getSuit() + ")";
	}
	
	//----------------------------------------------------
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		
		if (card.getRank().equals(Card.Rank.ACE)){
			System.out.println("Yay 1");
		}
		
		if (card.toString().equals("(ACE,SPADES)")){
			System.out.println("Yay 2");
		}

		if (card.getPoints()==1){
			System.out.println("Yay 3");
		}
		
		System.out.println(card.toString());
	}

}
