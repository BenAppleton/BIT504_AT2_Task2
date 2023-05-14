package com.TicTacToe;

// This import provides classes for creating and managing GUI's
import java.awt.*;
// This import provides classes for mouse events and other user interface events such as keyboard events
import java.awt.event.*;
// This import provides provides classes for creating panels like the JPanel. It is an extension of awt
import javax.swing.*;

public class GameMain extends JPanel implements MouseListener{
	//Constants for game 
	// This sets number of ROWS by COLS cell constants both to 3
	public static final int ROWS = 3;     
	public static final int COLS = 3;  
	// This sets the title of the game to 'Tic Tac Toe'
	public static final String TITLE = "Tic Tac Toe";

	//constants for dimensions used for drawing
	//cell width and height
	public static final int CELL_SIZE = 100;
	//drawing canvas (Setting the width and height of the game board)
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	//Noughts and Crosses are displayed inside a cell, with padding from the border
	public static final int CELL_PADDING = CELL_SIZE / 6;    
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	/*declare game object variables*/
	// the game board 
	private Board board;
	 	 
	/* Completed TODO: create the enumeration for the variable below (GameState currentState)
	 * This enum is used to keep track of the state of the game (is it playing, has cross won, has nought won, or a draw?)
	 */
	private enum GameState {
        PLAYING, DRAW, CROSS_WON, NOUGHT_WON
    }
	
	/* I have added to private GameState currentState the to the following = GameState.PLAYING;
	 * I did this so whenever the program is run, the initial state of the game is always 'playing', so you can start playing immediately.
	 */
	private GameState currentState = GameState.PLAYING; 
	// the current player
	private Player currentPlayer; 
	// for displaying game status message
	private JLabel statusBar;       
	

	/** Constructor to setup the UI and game components on the panel */
	public GameMain() {   
		
		/* Completed TODO: This JPanel fires a MouseEvent on MouseClicked so add required event listener to 'this'.          
		 * I added 'addMouseListener(this);'. This listens for mouse events such as clicks on the game board/window
		 * We use the keyword 'this' because it refers to the 'GameMain' object that is being created when you run the program. 
		 */
		addMouseListener(this);
	    
		// Setup the status bar (JLabel) to display status message       
		statusBar = new JLabel("         ");  
		// This sets the font of the JLabel input to bold and font size 14
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));     
		// This sets an empty boarder around the status bar which will add some padding and space between the text and boarder
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));   
		// This sets the opacity so the back ground colour (light grey) is visible
		statusBar.setOpaque(true);     
		// This sets the background colour of the JLabel to light grey
		statusBar.setBackground(Color.LIGHT_GRAY);  
		
		//layout of the panel is in border layout
		setLayout(new BorderLayout());  
		// This sets the status bar to the bottom of the game display
		add(statusBar, BorderLayout.SOUTH);
		// account for statusBar height in overall height
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));
		
		
		// Completed TODO: Create a new instance of the game "Board"class. HINT check the variables above for the correct name
		// This creates a new game board, and it allows to apply the methods above to the board
		board = new Board();
		
		//Completed TODO: call the method to initialise the game board
		// This initialises the game board by setting the board with empty cells and setting the current player to cross.
		initGame();
	}
	
	public static void main(String[] args) {
		    // Run GUI code in Event Dispatch thread for thread safety.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
				//create a main window to contain the panel
				JFrame frame = new JFrame(TITLE);
				
				// Completed TODO: create the new GameMain panel and add it to the frame
				// This creates the new instance of the GameMain panel
				GameMain gameMain = new GameMain();
				// This adds the game board panel to JFrame, without it the JFrame would not contain anything and appear blank.
                frame.add(gameMain);		
				
				
				// Completed TODO: set the default close operation of the frame to exit_on_close
                // This means that the program will exit when the user closes the JFrame (clicks the 'X' close icon of the JFrame)
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
				
                // 'frame.pack()' is used to trigger a layout calculation based on the preferred sizes of its components (Adjusts the size of the frame)
				frame.pack();       
				// This is used to center the frame on the users screen
				frame.setLocationRelativeTo(null);
				// This makes the frame visible
				frame.setVisible(true);
	         }
		 });
	}
	/** Custom painting codes on this JPanel */
	// Added capitals as the variables are case sensitive and sentence case was not recognised (PLAYING, DRAW, CROSS_WON, NOUGHT_WON)
	public void paintComponent(Graphics g) {
		// This calls the superclass paintComponent of JPanel to ensure it is painted correctly
		super.paintComponent(g);
		// This is used to set the background colour of the board to white
		setBackground(Color.WHITE);
		//ask the game board to paint itself
		board.paint(g);
		
		//set status bar message
		if (currentState == GameState.PLAYING) {     
			// Sets the colour for the font of the text in status bar to black while the game is playing
			statusBar.setForeground(Color.BLACK);  
			// This if statement is used to indicate in the status bar that is it the cross players turn
			if (currentPlayer == Player.Cross) {   
			
				// Completed TODO: use the status bar to display the message "X"'s Turn
				// Added this status message indicating X turn. This message will display at the bottom of the frame.
				statusBar.setText("'X' Turn");
			
			// This else statement is used to indicate in the status bar that is it the nought players turn 
			} else {    
				
				// Completed TODO: use the status bar to display the message "O"'s Turn
				// Added this status message indicating O turn. This message will display at the bottom of the frame.
				statusBar.setText("'O' Turn");
				
			}  
			// This else if statement is used for displaying text in the status bar if there is a draw
			} else if (currentState == GameState.DRAW) { 
				// Sets the colour for the font of the text in status bar to red if there is a draw
				statusBar.setForeground(Color.RED);  
				// This message will display if there is a draw
				statusBar.setText("It's a Draw! Click to play again.");   
			// This else if statement is used for displaying text in the status bar if cross won the game
			} else if (currentState == GameState.CROSS_WON) {      
				// Sets the colour for the font of the text in status bar to red if cross won
				statusBar.setForeground(Color.RED);  
				// This message will display if cross won
				statusBar.setText("'X' Won! Click to play again.");    
			// This else if statement is used for displaying text in the status bar if nought won the game
			} else if (currentState == GameState.NOUGHT_WON) {  
				// Sets the colour for the font of the text in status bar to red if there nought won
				statusBar.setForeground(Color.RED);  
				// This message will display if nought won
				statusBar.setText("'O' Won! Click to play again.");       
			}
		}
		
	
	  /** Initialise the game-board contents and the current status of GameState and Player) */
		public void initGame() {
			// This for loop is used to initialize each cell by iterating over each row of the game board
			for (int row = 0; row < ROWS; ++row) {          
				// This for loop is used to initialize each cell by iterating over each column of the game board
				for (int col = 0; col < COLS; ++col) {  
					// This sets all cells to empty when the game is initialised
					board.cells[row][col].content = Player.Empty;           
				}
			}
			// I updated the word 'Playing' to all caps. This sets the game state to playing when the game is initialised
			 currentState = GameState.PLAYING;
			 // This sets it so the cross player has the first move when the game is initialised
			 currentPlayer = Player.Cross;
		}
		
		
		/**After each turn check to see if the current player hasWon by putting their symbol in that position, 
		 * If they have the GameState is set to won for that player
		 * If no winner then isDraw is called to see if deadlock, if not GameState stays as PLAYING
		 */
		public void updateGame(Player thePlayer, int row, int col) {
			//check for win after play
			if(board.hasWon(thePlayer, row, col)) {
				
				// Completed TODO: check which player has won and update the currentstate to the appropriate gamestate for the winner
				// This 'if' statment checks if the winning player is cross
			    if(thePlayer == Player.Cross) {
			    	// This sets the current game state to 'CROSS_WON', which means the cross player won
			        currentState = GameState.CROSS_WON;
			    // This 'else' statement is used if the Nought player is the winner    
			    } else {
			    	// This sets the current game state to 'NOUGHT_WON', which means the nought player won
			        currentState = GameState.NOUGHT_WON;
			    }
			
			// This is used to check if the game is a draw
			} else if (board.isDraw()) {
					
				// Completed TODO: set the currentstate to the draw gamestate
					/* This 'else if' is used if the GameState is a draw, so neither player won.
					 *  So the above if and else statements are used to check if their is a winner, and 'else if' if there is no winner
					 */
					currentState = GameState.DRAW;
			}
			//otherwise no change to current state of playing
		}
		
				
	
		/** Event handler for the mouse click on the JPanel. If selected cell is valid and Empty then current player is added to cell content.
		 *  UpdateGame is called which will call the methods to check for winner or Draw. if none then GameState remains playing.
		 *  If win or Draw then call is made to method that resets the game board.  Finally a call is made to refresh the canvas so that new symbol appears*/
	
	public void mouseClicked(MouseEvent e) {  
	    // mouseX and mouseY are used to get the coordinates of where the click event happened and store them as int values for X and Y          
		int mouseX = e.getX();             
		int mouseY = e.getY();             
		// rowSelected is used to calculate row the position of where the user has clicked by using the value from mouseY divided by cell size            
		int rowSelected = mouseY / CELL_SIZE;      
		// colSelected is used to calculate column the position of where the user has clicked by using the value from mouseX divided by cell size  
		int colSelected = mouseX / CELL_SIZE;  
		// Updated 'Playing' to all caps. This if statement is used to check if the gamestate is playing
		if (currentState == GameState.PLAYING) {     
			// This if statement is used to check that the conditions are still suitable for gamestate to be playing. If they are not then the game will be restarted
			if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.Empty) {
				// This displays on the selected sell the content of the player who selected it 'X' or 'O' 
				board.cells[rowSelected][colSelected].content = currentPlayer; 
				// This updates the gamestate based on the current move made by the player               
				updateGame(currentPlayer, rowSelected, colSelected); 
				// If the current player is "Cross," switch it to "Nought" for the next turn
				if (currentPlayer == Player.Cross) {
					// If the current player is "Nought," switch it to "Cross" for the next turn
					currentPlayer =  Player.Nought;
				}
				// This else statement sets to currentPlayer to cross for the next turn to ensure that when the game restarts cross always goes first
				else {
					// current player is set to cross
					currentPlayer = Player.Cross;
				}
			}  
		//This is used if the game is over by won or draw and will reset the game board	
		} else {        
			// game over and restart              
			initGame();            
		}   
		
		// Completed TODO: redraw the graphics on the UI 
		// repaint() refreshes the display after a player has made a move. Updating the board to reflect the players moves
		repaint();     
	}
		
	// the below methods are not used as they are overwritten by the 'mouseListener' but they must remain for the game to run
	@Override
	public void mousePressed(MouseEvent e) {
		//  Auto-generated, event not used
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//  Auto-generated, event not used
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// Auto-generated,event not used
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Auto-generated, event not used
		
	}

}

