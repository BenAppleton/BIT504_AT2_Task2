package com.TicTacToe;

/**
 * Enumeration for the players move
 */
/* The Cell class uses this enum for populating the content of each cell to either (Empty, Nought, Cross)
 * The GameMain class uses this enum so it knows who the current player is and to always set cross as the first player 
 */
public enum Player {
	// This is used for naming the players. Empty represents an empty cell(s), Cross and Nought are the players
	Empty, Cross, Nought
}
