package com.TicTacToe;

// We use this import statement for setting the size pen stroke of the nought and cross symbols 
import java.awt.BasicStroke;
// This is used for setting the symbol colours
import java.awt.Color;
// this is used by the paint method for drawing on the screen
import java.awt.Graphics;
// This is used for drawing the nought and cross symbols in the paint method
import java.awt.Graphics2D;

public class Cell {
    //content of this cell (empty, cross, nought)
	Player content;
	//row and column of this cell
	int row, col;
	
	/** Constructor to initialise this cell with the specified row and col */
	public Cell(int row, int col) {
		
		/* Completed TODO: Initialise the variables row, col
		 * This specifies what cell was selected by the users input (mouse click)
		 * because the int row and col are used outside of this method we the keyword 'this' is used to help distinguish 
		 * a specific row and col (To identify the individual cell) instead of all row's and columns. 
		 */
	    this.row = row;
	    this.col = col;
		
		
		/*Completed TODO: call the method that sets the cell content to EMPTY
		 * The clear method is the last method in this class located on line 55
		 */
		clear(); 
	}
	

	/** Paint itself on the graphics canvas, given the Graphics context g */ 
	public void paint(Graphics g) {
		//Graphics2D allows setting of pen's stroke size
		Graphics2D graphic2D = (Graphics2D) g;
		// This sets the stroke size of the pen that draws the nought and cross in a cell
		graphic2D.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		//draw the symbol in the position. The oval symbol only needs to use this while the cross symbol uses this and the x2 and y2 because it has 2 end points
		int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;
		// This if statement is used to draw the cross symbol
		if (content == Player.Cross) {
			// This sets the colour of the cross symbol to red
			graphic2D.setColor(Color.RED);
			//This calculates the position of the cross symbol
			int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			// This is used to draw the two lines that make a cross symbol
			graphic2D.drawLine(x1, y1, x2, y2);
			graphic2D.drawLine(x2, y1, x1, y2);
		// This else if statement is used to draw the nought symbol
		}else if (content == Player.Nought) {
			// This sets the colour of the nought symbol to blue
			graphic2D.setColor(Color.BLUE);
			// This is used to draw the oval symbol
			graphic2D.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
		}
	}
	
	/** Set this cell's content to EMPTY */
	public void clear() {
		
		/* Completed TODO: Set the value of content to Empty (Remember this is an enum)
		 * We're using 'this.content =' over 'content =' despite having the same result because it is more specific
		 * The keyword 'content' because the Player enum has 3 values that could display in a cell 'Empty, Nought, Cross
		 * So we want to specify 'empty' with Player.empty when we want a empty cell
		 */
		this.content = Player.Empty;
		
	}		
}
