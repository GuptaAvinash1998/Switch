
/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Deck.java
 *
 * Description: Class representing a deck of cards with basic functionalities
 * of shuffling, adding, dealing, etc.
 *
 * TASK: Comment using JavaDoc and show the Big-O runtime of each method.
 * Code on this file should NOT be modified.
 * 
 ***************************************************************************/

public class Deck<T extends Card> {
	
	private Hand<T> setOfCards; //This is a reference to the hand
	
/**
 * This is the constructor that initializes the hand
 */
	public Deck(){
		//O(1)
		setOfCards = new Hand<T>();
	}

/**
 * In this method, card c is added to the deck of cards
 * @param c This is the card that is being added
 * @return returns either true or false based on whether the card can be added or not or not
 */
	public boolean addCard(T c){
		//O(1)
		if (hasCard(c)) //If the card exists then it cannot be added again
			return false;
		setOfCards.addCard(c); //If it doesn't then the card is added
		return true;
	}
	
/**
 * This checks if the card exists or not
 * @param c This is card that we are checking exists or not
 * @return returns either true or false based on whether the card exists or not
 */
	public boolean hasCard(T c){
		//O(1)
		return (setOfCards.indexOf(c)!=-1); //returns the index if found
	
	}

/**
 * This is used to shuffle the deck of cards
 */
	public void shuffle() {
		//O(N)
		for ( int i = setOfCards.numCards()-1; i >= 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));//generates a random number
	        T temp = setOfCards.getCard(i);
            	setOfCards.setCard(i, setOfCards.getCard(rand)); //if that number is a valid index then it will fetch that card from the main deck of cards puts it on top
            	setOfCards.setCard(rand, temp);
	    }
	}

/**
 * This is used to deal the cards to the players
 * @return This returns the card that being handed to the player
 */
	public T dealNextCard() {
		//O(1)
		if(setOfCards.numCards()==0) return null; //If the number of cards are 0 the there's nothing to deal
		T temp = this.setOfCards.removeCard(setOfCards.numCards()-1); //Else that card is fetched and removed from the deck and handed over to the player
		return temp;
	}

/**
 * This checks the number of cards in the deck are 0 or not 
 * @return returns either true or false based on whether there are cards in the deck
 */
	public boolean isEmpty() {
		//O(1)
		return this.setOfCards.numCards() == 0;
	}

/**
 * This counts the number of cards that exist in the deck
 * @return This returns the total number of cards
 */
	public int cardCount(){ 
		//O(1)
		return this.setOfCards.numCards();
	}
	
/**
 * Returns a string representation of the deck along with the number of cards in it
 */
	public String toString(){
		//O(1)
		StringBuilder sb = new StringBuilder("Deck ");
    		int numCards = cardCount();
    		if (numCards ==0){
    			sb.append("currently with no cards.");
    		}
    		else{
    			sb.append("with "+numCards+ " cards:\n");
    			sb.append(setOfCards.toString());
    		}
    		return sb.toString();
  	}

}

