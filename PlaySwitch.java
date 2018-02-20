
/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: PlaySwitch.java
 *
 * Description: This file SHOULD NOT be modified. This provides the 
 * implementation of a simple game with cards.  The game follows these steps:
 * 1. Creating a full deck of 52 cards
 * 2. Creates players and adds them to board
 * 3. Deal all cards to users one by one (all players may not receive same number of cards)
 * 4. Switch the first n cards between the players
 * 5. Player with the higher total points of all cards in hand is the winner
 * 
 ***************************************************************************/
import java.util.Scanner;

class PlaySwitch{

/**
 * Initializes the deck of 52 cards
 * @param deck This is what is being initialized
 */
		public static void init_deck(Deck<CardSwitch> deck){
			//O(N^2)
			for(Card.Suit s: Card.Suit.values()) {
				for(Card.Rank r: Card.Rank.values()) {
					CardSwitch card = new CardSwitch(r,s);//Gets the rank and suit from the Card class and assigns it to a card
					deck.addCard(card); //Then adds it
				}
			}
			deck.shuffle();
		}
		
/**
 * Initializes the players playing the game
 * @param myBoard This is the board where the players are going to be initialized to
 */
		//create players
		public static void init_players(BoardSwitch<CardSwitch> myBoard){
			//O(N)
			Scanner input = new Scanner(System.in);
			System.out.print("Enter the number of players: ");
			int n = input.nextInt(); //Gets the user input for the number of players
			for(int i=0; i<n; i++) {
				System.out.println("Enter Name of Player "+(i+1)+": ");
				String name = input.next(); //Gets the names of the players from the user
				myBoard.addPlayer(new Player<CardSwitch>(name)); //Adds the players to the board
			}
			
		}
		
/**
 * Deals the Cards to the players
 * @param myBoard
 */
		// deal to players, each gets numToDeal cards 
		public static void dealCards(BoardSwitch<CardSwitch> myBoard){
			//O(N)
			while(!myBoard.getDeck().isEmpty()) { //As long as the deck is not empty, the cards will be distributed as evenly as possible to all the players
					CardSwitch card = myBoard.getDeck().dealNextCard();
					myBoard.getCurrentPlayer().receiveCard(card);
					myBoard.changeTurn();
			}
		}
		
/**
 * Here the players play the game
 * @param myBoard This is where the game is being played
 */
		public static void playRound(BoardSwitch<CardSwitch> myBoard){
			//O(N)
			int numPlayer = myBoard.getNumPlayers(); //Gets the number of players
			//switch one card between users
			for (int i=0; i<numPlayer; i++){
				
				System.out.println("current player: " + myBoard.currentPlayer);
				CardSwitch card = myBoard.getCurrentPlayer().playCard(0); //Plays the card
				System.out.print("switch from " + myBoard.getCurrentPlayer().getName()+": "); //Switches turns
				System.out.print(" card " + card+", ");
				myBoard.changeTurn();
				myBoard.getCurrentPlayer().receiveCard(card); //Gets the card
				System.out.println(" switch to " + myBoard.getCurrentPlayer().getName());	 
			}						
		}
		

		public static void main(String[] args) {
		
			Deck<CardSwitch> deck = new Deck<CardSwitch>();
			init_deck(deck);
			
			BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
 
			init_players(myBoard);
		
			Scanner input = new Scanner(System.in);
		
			// deal all cards
			dealCards(myBoard);
			
			System.out.println("How many cards should be switched?");
			int numSwitches = input.nextInt();
			
			//switch cards
			System.out.println("-----------------------------------");
			for(int i=0; i<numSwitches; i++) {
				System.out.println("-Starting ROUND "+(i+1)+"-");
				playRound(myBoard);
			}

			System.out.println("-----------------------------------");


			Player<CardSwitch> winner = myBoard.findWinner();
			System.out.println("Winner is: "+winner.getName()+" with "+winner.getPoints() + " points" );

	}

}


