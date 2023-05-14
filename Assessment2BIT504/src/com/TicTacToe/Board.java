package com.TicTacToe;

// We use this to colour the grid lines in the paint() method
import java.awt.Color;
// We use this to draw the board and its content, it's called in the paint() method
import java.awt.Graphics;


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
		
		// This starts a for loop to iterate (go through) the rows of the cells array.
		for (int row = 0; row < GameMain.ROWS; ++row) {
			// This is a nested loop and it iterates through the columns of the cells array.
			for (int col = 0; col < GameMain.COLS; ++col) {
				// This initializes each element of the cells array with a new Cell object
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
		 // This checks if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer )
			// Returning true indicates that the player won the game this way
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
			// Returning true indicates that the player won the game this way
			return true;
		 
		
		// Complete TODO: Check the diagonal in the other direction
		// This checks for a diagonal line of 3 cells from top right to bottom left, while the above checks from top left to bottom right
	    if (cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer) {
	    	// Returning true indicates that the player won the game this way
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
		/* The first two for loops are used to draw the grid line on the board (Vertical and Horizontal)
		 * The third for loop is used to draw the cells
		 */
		// This is used to colour the gird lines grey
		g.setColor(Color.gray);
		// This for loop is used to iterate over each row of the game board
		for (int row = 1; row < GameMain.ROWS; ++row) {   
			// This draws rectangles vertically (in rows)
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDHT_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		// This for loop is used to iterate over each column of the game board
		for (int col = 1; col < GameMain.COLS; ++col) {   
			// This draws rectangles horizontally (in columns)
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDHT_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		// This for loop is used to draw the cells. The for loop iterates through the rows
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			// This for loop iterates through the columns
			for (int col = 0; col < GameMain.COLS; ++col) {  
				// This paints the content of the cell (empty, nought or cross)
				cells[row][col].paint(g);
			}
		}
	}
}
