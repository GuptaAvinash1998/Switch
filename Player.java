
/**************************************************************************
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Player.java
 *
 * Description: This class represents a Player that is playing the game. Each player has a name, a set of cards, a number of points and a reference to the next player.
 * The players are connected to each other through a circular linked list 
 * 
 ***************************************************************************/
class Player <T extends Card> {
	
	// required fields
	private String name; //Represents the name of the player
	private int points; //Represents the points of the player
	private Hand<T> hand; //This is a reference to the hand class. In the hand class, we define an array of cards that the player holds in his or her hand
	private Player<T> next; //This is the reference to the next player
	
/**
 * This is the constructor for the class where we initialize the name, points, hand and the reference
 * @param name This is the name of the player
 */
	public Player(String name){
		//constructor
		//O(1)
		this.name = name;
		this.points = 0;
		this.hand = new Hand<>();
		next = null; 
		
	}
/**
 * This method is used to set reference to the next player in the list		
 * @param p This is the player that next is going to be set to
 */
	public void setNext(Player<T> p){
		//O(1)
		this.next = p; //sets next to p
		
	}
	
/**
 * This method gets the next player in the linked list
 * @return
 */
	public Player<T> getNext(){
		//return next player
		//O(1)
		return next;
	}
	
/**
 * This method is used to check whether there is a player after a certain specified player
 * @return returns either true or false based on whether there is a next player or not
 */
	public boolean hasNext() {
		// whether there is a player after me
		//O(1)
		if(next != null) { //If the reference is null then it returns false
			return true;
		}else {
			return false;
		}
		
	}
	
/**
 * Returns the points that the player has based on the cards that he or she is holding in his or her hand
 * @return returns the total number of points
 */
	@SuppressWarnings("unchecked")
	public int getPoints(){
		
		//O(2N)
		T[] temp = (T[]) new Card[hand.numCards()]; //creates a temporary array
		
		int i = 0;
		
		for(i=0;i<hand.numCards();i++) { //Iterates through the cards array and puts it into the temporary array
			temp[i] = hand.getCard(i);
			
		}
		
		points = 0;
		for(i=0;i<temp.length;i++) { //Iterates through the temporary array
			
			points += temp[i].getPoints();//For every card it iterates through, the points will get updated based on the value that the card possess.
			
		}
		
		return points;
	}
	
/**
 * This returns the name of the player
 * @return returns the name of the player
 */
	public String getName(){
		//O(1)
		return name;
	}
	
/**
 * In this method, the player gets a card c and then adds it to the array of cards that the player has in his or her hand
 * @param c This is the card that is being added to the array
 * @return returns a boolean value if the addition is successful
 */
	public boolean receiveCard(T c){
		//O(1)
		hand.addCard(c); //adds the card to the array of cards
		points += c.getPoints();//Every time a card is received
		return true;
	}

/**
 * This method checks if a card c exists in the set of cards that the player is holding in his or her hand
 * @param c This is the card that we checking exists in the array
 * @return returns a boolean value either true or false based on whether the card exists or not
 */
	public boolean hasCard(T c){
		//O(1)
		if(hand.indexOf(c) == -1) { //If the card does not exist, then the value of the index will be set to -1 and it will return false
			return false;
		}else {
			return true;
		}
	}
	
/**
 * In this method the player is going to play card C  
 * @param c This is the card the player is going to play
 * @return returns a boolean value either true or false based on whether the card exists or not. If the card exists, then the card is going to be removed
 */
	public boolean playCard(T c){
		//O(1)
		if(hand.indexOf(c) == -1) { //Checks if the card exists in the array or not
			return false;
		}else {
			this.points -= c.getPoints(); //If it does then the when the card is played, the total number of points that the player has will decrease based on the value of the points the card has
			return hand.removeCard(c); //removes the card from the deck
		}
	}

/**
 * This method plays the card as well but the card will be referenced based on the index
 * @param index This is the index of the card that is going to be played
 * @return returns the card that is going to be played
 * @throws If the card cannot be found, i.e. if the index is not valid then it will throw an exception
 */
	public T playCard(int index){
		//O(1)
		T playedCard; //Is a variable that holds the card that is going to be played
		boolean temp = false; //This a temporary variable that will hold a boolean value
		
		try {
			playedCard = hand.getCard(index); //If the index is found then the card will be fetched and that card will be stored in playedCard
			temp = playCard(playedCard); //Played card will then be sent to the previous playCard method where the points will be decreased and removed from the array of cards that he or she has
			return playedCard; //Returns the card that is going to be played
		}catch(RuntimeException e) {
			throw e;
		}

	}
	//---------------------------------------------------
	//example test code... edit this as much as you want!
	// you will need working CardSwitch and Hand classes to run the given code
	
/**
 * This returns the player's name
 * @return Returns the player's name
 */
	public String toString(){
		// Not required; edit for your own testing 
		//O(1)
		return "Player "+ name;
	}


	public static void main(String[] args) {
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		player1.receiveCard(card2);
		player1.receiveCard(card3);
		
		player2.receiveCard(card1);
		player1.setNext(player2);

		if (player1.getName().equals("Tom") && player1.getNext() == player2){
			System.out.println("Yay 1");
		}
		if (player1.hasCard(card2) == true && player1.getPoints() == 19){
			System.out.println("Yay 2");
		}
		if ((player2.hasNext()==false) && player1.playCard(0) == card2){
			System.out.println("Yay 3");
		}
	
	}


}
