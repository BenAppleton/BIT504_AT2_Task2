package com.TicTacToe;

import java.awt.Color;
import java.awt.Graphics;

import com.TicTacToeGame.GameMain;
import com.TicTacToeGame.Player;


public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** Constructor to create the game board */
	public Board() {
		
	 // Completed TODO: initialise the cells array using ROWS and COLS constants 
		/* This tells the game board how many rows there are and how many column there are
		 * We're calling the Rows and Cols from the GameMain class, because their values have been specified in that class
		 */
		cells = new Cell[GameMain.ROWS][GameMain.COLS];
		
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}

	 /** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw() {
		 
		// Complete TODO: Check whether the game has ended in a draw. 
	    // This checks whether any of the cells in the board grid are Player.Empty
	    for (int row = 0; row < GameMain.ROWS; ++row) {
	    	// This loops through each column in the row
	        for (int col = 0; col < GameMain.COLS; ++col) {
	        	// This checks if the current cell being examined is an empty cell
	            if (cells[row][col].content == Player.Empty) {
	            	// if an EMPTY cell is found, it will return false (game is not a draw)
	                return false; 
	            }
	        }
	    }
	    
	    // This means all cells are non-empty, so it is a draw
	    return true;
	}
	
	/** Return true if the current player "thePlayer" has won after making their move  */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		 // check if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer )
			return true; 
		
		 // Complete TODO: Check if the player has 3 in the playerCol.
		/* This checks if the player has all 3 cells (x or o) in a column.
		 * A column in the context of this game is a a horizontal line of cells 
		 * While a row is a vertical line of cells
		 */
	    if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer) {
	    	// Returning true indicates that the player won the game this way
	        return true;
	    }		
		
		
		 // 3-in-the-diagonal
		if( cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true;
		 
		
		// Complete TODO: Check the diagonal in the other direction
		// This checks for a diagonal line of 3 cells from top right to bottom left, while the above checks from top left to bottom right
	    if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) {
	        return true;
	    }		

		
		//no winner, keep playing
		return false;
	}
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDHT_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDHT_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	}
	

}
