/**************************************************************************
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: BoardSwitch.java
 *
 * Description: This class is a child class of the abstract class Board. The abstract methods of the Board class are defined here.
 * A board has a reference to the current player, the number of players playing the game, the deck of cards and the cards present on the board
 * 
 ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{
	
	private Player<T> head = null; //This a reference to the head of the linked list
	private Player<T> tail = head;//This is a reference to the tail of the linked list
	private Player<T> cntr; //This is a pointer that is used while iterating the linked list

/**
 * This is the constructor and is used to initialize the board. The board starts with 0 players
 * @param deck This is the deck of cards used to play in the game
 */
	public BoardSwitch(Deck<T> deck){
		//O(1)
		super(deck);
		this.numPlayer = 0;
	}

/**
 * This method returns the current player
 * @return returns the current player
 */
	@Override
	public Player<T> getCurrentPlayer() {
		// return the current player
		// O(1)
		return this.currentPlayer;
	}

/**
 * This method returns the number of players in the game
 * @return returns the number of players in the game
 */
	@Override
	public int getNumPlayers() {
		// return how many players 
		// O(1)
		return this.numPlayer;
	}
	
/**
 * This method returns the deck of cards
 * @return returns the deck
 */
	@Override
	public Deck<T> getDeck(){
		//return the current deck
		// O(1)
		return this.deck;
	}

/**
 * Changes the currentPlayer to the next player
 * @return returns a boolean value either true or false based on whether the next player exists or not
 */
	@Override
	public boolean changeTurn() {
		// O(1)
		if(currentPlayer.getNext() == null) { //If the next is not null then the player exists and the currentPlayer will be updated
			return false;
		}else {
			currentPlayer = currentPlayer.getNext();
			return true;
		}
	}
	
/**
 * This method adds the player to the list. Here the player is added to the left of the currentPlayer
 * @param x This is the player that is being added
 */
	@Override
	public void addPlayer(Player<T> x) {
		// O(N)
		
		if(numPlayer == 0) {//Since this is the first player to be added, he or she will be the head of the linked list
			
			head = x;
			super.currentPlayer = head;
			super.currentPlayer.setNext(head);
			tail = head; //Since the list is circular, the last node will point to the first node
			super.numPlayer++;//Since the player got added the number of players will increase
			
		}else if(numPlayer == 1) {//Since you are adding to the left of the already existing node, head gets reassigned to the new node and the tail is the current player, and the tail is pointing to the head
			
			head = x;
			x.setNext(currentPlayer);
			super.currentPlayer.setNext(x);
			tail = super.currentPlayer;
			super.numPlayer++;//Since the player got added the number of players will increase
			
		}else { //If there are more than 2 players, then it will find currentPlayer by iterating through the list and then adding it to the left of currentPlayer
			
			cntr = head; //Starts the counter at the head
			Player<T> cntr1 = head; //This is a temporary counter that acts as a permanent reference to the head
			
			
			for(int i=0;i<numPlayer;i++) {
				
				if(cntr.getNext() == currentPlayer) { //If the next player is the current player then it will add Player X between the player before currentPlayer and currentPlayer
					cntr.setNext(x);
					x.setNext(currentPlayer);
					numPlayer++;
					tail.setNext(cntr1);
					break; //Once the player is addded the process stops
				}else {
					cntr = cntr.getNext();
				}
				
			}
		}		
	}

/**
 * This method is used to find which player wins the game. The player wins the game if he or she has the most number of points
 * @return This returns the winner of the game
 */
	public Player<T> findWinner(){
		// O(N)
		
		cntr = head; //Sets the counter to the head
		Player<T> winner = null; //Will store the winner of the game
		int equalCase = 0; //If there are two players with an equal score then this will get incremented
		Player<T> temp1 = null; //Both are temporary references
		Player<T> temp2 = null;
		
		for(int i=0;i<numPlayer;i++) {
			
			if(cntr.getPoints() > cntr.getNext().getPoints()) { //Starts with the head, if the the player has a higher score then he or she will be the winner
			
				winner = cntr; //As the loop is running if by chance there is another player with a higher score then winner will be updated
				continue;
			}
			
			if(cntr.getPoints() < cntr.getNext().getPoints()) { //If the player has a lower scored compared to the person next to him or her then the counter moves to the next person
				cntr = cntr.getNext();
			}
			
			if(cntr.getPoints() == cntr.getNext().getPoints()) { //If the points are equal then variable increments and the 2 players will be stored in two separate references
			
				equalCase++;
				temp1 = cntr;
				temp2 = cntr.getNext();
				cntr = cntr.getNext();
			}
		}
		
		if(equalCase > 0) { //If there is an equal case then it enters the condition and then the winner is determined based on the lexicographic comparison between the two names 
			
			if(temp1.getName().compareToIgnoreCase(temp2.getName()) < 0) {
				winner = temp1;
			}else {
				winner = temp2;
			}
		}else {
			
			return winner;
		}
		
		return winner;
	}

	//-----------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
	
	public static void main(String[] args) {
		Deck<CardSwitch> deck = new Deck<CardSwitch>();
		PlaySwitch.init_deck(deck);
			
		BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		myBoard.addPlayer(player1);
		
		if (myBoard.getNumPlayers() ==1  && myBoard.getCurrentPlayer() == player1
			&& player1.getNext() == player1){
			System.out.println("Yay 1");
		}

		myBoard.addPlayer(player2);
		if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
			&& (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
			System.out.println("Yay 2");
		}
		
		player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
		player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
		player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
		player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));

		if (player1.getNext() == player2 && player2.getNext() == player1
			&& myBoard.findWinner() == player2){
			System.out.println("Yay 3");
		}
		
	
	}
	

}
