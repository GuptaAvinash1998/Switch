
/**************************************************************************
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Hand.java
 *
 * Description: This class represents what the hand of a player who is playing the game looks like.
 * A player's hand has a set of cards that decreases or increases in size. Since the size constantly changes
 * It implements the Array list data structure.
 * 
 ***************************************************************************/

public class Hand<T extends Card>{

	

	private T [] cards; //Represents the set of cards in the player's hand
	private int numCards;//number of cards in hand
	
/**
 * This is the constructor that initializes the hand. At the beginning the player's hand will have zero cards
 */
	@SuppressWarnings("unchecked")
	public Hand() {
		//O(1)
		super();//Number of cards of an empty hand is 0
		numCards = 0;
		cards = (T[]) new Card[numCards];
		

	}

/**
 * Returns the number of cards in the player's hand
 * @return This returns the total number
 */
	public int numCards(){
		// return the number of cards
		return numCards;
		// O(1)

	}	
	
	
/**
 * This is used to get a card that maps to a given index
 * @param index This is the location of the card to be pulled
 * @return Returns the card if found
 */
	public T getCard(int index){
		// O(1)
		
		if(cards.length == 0) { //If there are no cards in the player's hand then it will throw an exception
			throw new RuntimeException("Card list is empty");
		}
		
		if(index < 0 || index > cards.length) { //If the index is valid then it will return the card
			throw new RuntimeException("Invalid index");
		}else {
			return cards[index];
		}
	}

/**
 * This is used to set the card  C at particular given index
 * @param index This is the index that we are setting the card at
 * @param c This is the card that we are setting
 */
	public void setCard(int index, T c){
		// O(1)
		
		if(index < 0 || index > cards.length) { //If the index is valid then it will set the card at that index else it will throw an exception
			throw new RuntimeException("Invalid index");
		}else {
			cards[index] = c;
		}

	}

/**
 * This is used to add a card to the list of cards
 * @param c This is the card that we are adding
 */
	@SuppressWarnings("unchecked")
	public void addCard(T c){
		// O(N)
		
		if(numCards == 0) { //If the number of cards in the player's hand is zero then it will be added
			
			numCards++;
			cards = (T[]) new Card[numCards];
			cards[0] = c;
			return;
		}
		
		if(this.numCards == this.cards.length) { //If the number of cards in the players hand is same as the number of cards
			
			
			T[] bigger_Card_Set = (T[]) new Card[5+this.numCards];//creates a new Array 
			
			for(int i=0;i<this.cards.length;i++) { //transfer all the old elements to the new bigger array
				bigger_Card_Set[i] = cards[i];
			}
			
			cards = bigger_Card_Set; //reassign the old array to the new one
		}
		
		cards[numCards] = c; //Adds the element
		
		numCards++; //Increases the number of cards in the players hand
	}
	
/**
 * This is used to find the index of a card C in the collection of cards
 * @param c This is the card that we are looking for 
 * @return Returns the index of the card
 */
	public int indexOf(T c){
		 // O(N)
		
		for(int i=0;i<cards.length;i++) {
			
			if(cards[i] == c) {//Iterates through the array, if the element is found then it will return its index
				return i;
			}
		}
		
		return -1;//If it is not found then it will return -1
	}
	
/**
 * This is a separate function that transfers all the elements from one array to the other
 * @param main The array the elements are being transfered from
 * @param sub The array the elements are being transfered to
 */
	 private void transfer(T[] main, T[] sub) {//transfers elements from the sub array to the main array
		 //O(N)
		 for(int i=0;i<sub.length;i++) {//Iterates through the array and transfers all the elements
			 sub[i] = main[i];
		 }
	 }
	 
/**
 * This is used to remove the card the collection of cards at a given index
 * @param index This is the location of the card that is going to be removed
 * @return Returns the removed card
 */
	@SuppressWarnings("unchecked")
	public T removeCard(int index){
		// O(N)
		
		T removed_card; //stores the the card that has to be removed
		
		if(index < 0 || index > cards.length) { //If the index is not valid then it will throw an exception
			throw new RuntimeException("Invalid index");
		}
		
		removed_card = cards[index];//stores the card to be removed in a variable of type T
		
		for(int i= index; i< (cards.length)-1;i++) { //To fill in the empty space and remove the card from the array, the card at that index will be replaced by the next card until it reaches the end
			
			if(i+1 < cards.length) {
				
				cards[i] = cards[i+1];
			}
		}
		
		T[] temp = (T[]) new Card[cards.length-1];
		
		transfer(cards,temp);//after removing the required element from the deck, the original array(cards) 
		//will have a duplicate of the last element so I created another function that will take this array 
		//and another temporary one with a size of len-1 and copied all the elements of cards into this except 
		//the last duplicate
		
		cards = temp; //Then I reassigned cards to the temporary array
		
		numCards--; //Since the card got removed the number cards the in the player's hand will decrease by 1
		
		return removed_card;
	}
	
/**
 * This find the index of the card in the collection
 * @param c This is the card that we are looking for
 * @return Returns the index of card
 */
	private int findIndex(T c) {
		//O(N)
		for(int i=0;i<cards.length;i++) {//Iterates through the array
			
			if(cards[i] == c) { //if the card is found then it will return its index
				return i;
			}
		}
		
		return -1;
	}
	
/**
 * This method is used to remove a card from the collection except this time we are specifying the card instead of giving its index
 * @param c This is that card that we are removing
 * @return Returns either true or false based on whether the card has been removed or not
 */
	public boolean removeCard(T c){
		// O(N)
		
		int index = findIndex(c); //Find the index of the card
		
		if(index == -1) { //if the index is not found then it will return false
			return false;
		}else {
			for(int i= index; i< (cards.length)-1;i++) { //If found then it will iterate through the array 
				
				if(i+1 < cards.length) { //Replace the card with the next
					
					cards[i] = cards[i+1];
				}
			}
			
			@SuppressWarnings("unchecked")
			T[] temp = (T[]) new Card[cards.length-1]; //after removing the required element from the deck, the original array(cards) 
			//will have a duplicate of the last element so I created another function that will take this array 
			//and another temporary one with a size of len-1 and copied all the elements of cards into this except 
			//the last duplicate
			
			transfer(cards,temp); //Transfers the cards
			
			cards = temp; //reassigns the array
			
			numCards--; //Since the card was removed the total number of cards will decrease
			
			return true;
		}

	}
	
	
  
	// --------------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need a working CardSwitch class to run the given code


/**
 * Returns the string representation of the hand with the number of cards
 */
	@Override
	public String toString(){
		// return string representation of hand
		// update if you want to include information for all cards in hand
		//O(1)
		return "Hand with "+numCards+" cards";
		
		
  	}


	public static void main(String[] args) {
	
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		
		
		Hand<CardSwitch> myHand = new Hand<CardSwitch>();
		myHand.addCard(card1);
		myHand.addCard(card2);
		
		if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
			System.out.println("Yay 1");
		}
		
		myHand.addCard(card3);
		
		if ( card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
			System.out.println("Yay 2");
		}

		if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
			System.out.println("Yay 3");
		}
		
		CardSwitch removedCard = myHand.removeCard(0);
		
		System.out.println(removedCard);
		
		System.out.println(myHand.numCards);

	}


}
