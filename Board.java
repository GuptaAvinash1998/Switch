/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Board.java
 *
 * Description: Abstract Board class from which a board class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Board<T extends Card> {
	
	protected Player<T> currentPlayer;//Represents the current player
	protected int numPlayer;//Represents the number of players
	protected Deck<T> deck;//Represents the deck on the board
	protected Hand<Card> cardOnBoard;//Represents the cards on the board
	
/**
 * This is the constructor that initializes the current player to no one, number of players to 0	
 * @param deck
 */
	public Board(Deck<T> deck){
		//O(1)
		this.currentPlayer = null;
		this.numPlayer = 0;
		this.deck = deck;
	}
	
	abstract Player<T> getCurrentPlayer();
	
	abstract int getNumPlayers();
	
	abstract Deck<T> getDeck();
	
	abstract boolean changeTurn();
	
	abstract void addPlayer(Player<T> x);

}
