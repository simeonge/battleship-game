import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * This is the controller for the Battleship game. It responds to buttons clicks
 * from the user and updates the model, gets information from the model and
 * updates the view accordingly.
 */
public final class BattleshipController {
	// Fields
	/**
	 * String that says "Player 1" for dialog boxes.
	 */
	private static String p1 = "Player 1";

	/**
	 * String that says "Player 2" for dialog boxes.
	 */
	private static String p2 = "Player 2";

	/**
	 * The type of ship. Selected in the view and used to change the model.
	 */
	private static ShipEnum ship = null;

	/**
	 * The direction of the ship. Selected in the view and used to change the
	 * model.
	 */
	private static CommandEnum direction = CommandEnum.up;

	/**
	 * An instance of the model.
	 */
	private static BattleshipGame game = new SolidBattleshipGame();

	/**
	 * An instance of the view for player 1.
	 */
	private BattleshipView player1;

	/**
	 * An instance of the view for player 2.
	 */
	private BattleshipView player2;

	// Constructor
	/**
	 * Private constructor for this utility class. It creates two instances of
	 * the view with a window for each, asks player 1 to start and adds
	 * listeners to the appropriate buttons.
	 */
	private BattleshipController() {
		// create instances
		player1 = new BattleshipView("Battleship - Player 1", 1);
		player2 = new BattleshipView("Battleship - Player 2", 2);

		// terminate the game if the window is closed
		player1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set visibility and position
		player1.setVisible(true);
		player2.setLocation(player1.getWidth(), 0);
		player2.setVisible(true);

		// ask player 1 to start
		BattleshipView.askPlayerToStart(p1);

		/*
		 * Add listeners to ship and orientation buttons for player 1. Now
		 * player 1 is ready to place ships.
		 */
		addShipListeners(player1);
		addOrientationListeners(player1);
	}

	// Methods
	/**
	 * Adds listeners to player's ship buttons.
	 * 
	 * @param player
	 *            the View instance whose ship buttons will have listeners
	 * @ensures the ship buttons have listeners
	 */
	private void addShipListeners(BattleshipView player) {
		final int numOfShips = 5;
		for (int i = 0; i < numOfShips; i++) {
			player.addShipButtonListener(new ShipActionListener(), i);
		}
	}

	/**
	 * Adds listeners to player's orientation buttons.
	 * 
	 * @param player
	 *            the View instance whose orientation buttons will have
	 *            listeners
	 * @ensures the orientation buttons have listeners
	 */
	private void addOrientationListeners(BattleshipView player) {
		final int directions = 4;
		for (int i = 0; i < directions; i++) {
			player.addOrientationButtonListener(
					new OrientationActionListener(), i);
		}
	}

	/**
	 * Adds listeners to player's grid buttons.
	 * 
	 * @param player
	 *            the View instance whose grid buttons will have listeners
	 * @ensures the grid buttons have listeners
	 */
	private void addGridListeners(BattleshipView player) {
		for (int i = 0; i < BattleshipView.SIZE; i++) {
			player.addGridButtonListener(new ButtonActionListener(), i);
		}
	}

	/**
	 * Removes listeners from player's grid buttons.
	 * 
	 * @param player
	 *            the View instance whose grid buttons will not have listeners
	 * @ensures the grid buttons don't have listeners
	 */
	private void removeGridListeners(BattleshipView player) {
		for (int i = 0; i < BattleshipView.SIZE; i++) {
			// remove all action listeners
			for (ActionListener al : player.getGridButton(i)
					.getActionListeners()) {
				player.getGridButton(i).removeActionListener(al);
			}
		}
	}

	// Nested classes implementing ActionListener
	/**
	 * Nested class that acts as the action listener for the ship buttons which
	 * indicate which type of ship will be placed on the grid.
	 */
	private class ShipActionListener implements ActionListener {
		// actionPerformed method
		@Override
		public void actionPerformed(ActionEvent e) {
			// needed constants
			final int three = 3, four = 4;
			// get command
			String command = e.getActionCommand();
			int i = new Integer(command.substring(1));

			if (command.charAt(0) == 'a') { // for player 1
				/*
				 * Disable the clicked button and set all to invisible so that
				 * the player is forced to place a ship.
				 */
				player1.getShipButton(i).setEnabled(false);
				player1.getShipButton(0).setVisible(false);
				player1.getShipButton(1).setVisible(false);
				player1.getShipButton(2).setVisible(false);
				player1.getShipButton(three).setVisible(false);
				player1.getShipButton(four).setVisible(false);
				// add grid listeners so that the player can place ship
				addGridListeners(player1);
			} else { // for player 2
				/*
				 * Disable the clicked button and set all to invisible so that
				 * the player is forced to place a ship.
				 */
				player2.getShipButton(i).setEnabled(false);
				player2.getShipButton(0).setVisible(false);
				player2.getShipButton(1).setVisible(false);
				player2.getShipButton(2).setVisible(false);
				player2.getShipButton(three).setVisible(false);
				player2.getShipButton(four).setVisible(false);
				// add grid listeners so that the player can place ship
				addGridListeners(player2);
			}

			/*
			 * Determine which ship the player wants to add based on which
			 * button was clicked.
			 */
			switch (i) {
			case 0:
				ship = ShipEnum.Aircraft_Carrier;
				break;
			case 1:
				ship = ShipEnum.Battleship;
				break;
			case 2:
				ship = ShipEnum.Cruiser;
				break;
			case three:
				ship = ShipEnum.Submarine;
				break;
			case four:
				ship = ShipEnum.Destroyer;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Nested class that acts as the action listener for the orientation buttons
	 * which indicate in which direction a ship will be placed on the grid.
	 */
	private static class OrientationActionListener implements ActionListener {
		// actionPerformed method
		@Override
		public void actionPerformed(ActionEvent e) {
			// needed constants
			final int three = 3;

			// get command
			String command = e.getActionCommand();
			int i = new Integer(command.substring(1));

			// set the direction according to the command
			switch (i) {
			case 0:
				direction = CommandEnum.up;
				break;
			case 1:
				direction = CommandEnum.down;
				break;
			case 2:
				direction = CommandEnum.left;
				break;
			case three:
				direction = CommandEnum.right;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Nested class that acts as the action listener for the grid buttons which
	 * either indicate the starting position of a ship that will be placed, or a
	 * shot fired to attempt to hit a ship.
	 */
	private class ButtonActionListener implements ActionListener {
		// private methods
		/**
		 * Clears player's grid by getting the token for each button in the grid
		 * and making the icon water if it's empty, ship otherwise.
		 * 
		 * @param player
		 *            the View instance whose grid will be cleared
		 * @param playa
		 *            the CommandEnum for the player
		 * @ensures the grid displays where the ships are located
		 */
		private void clearIcons(BattleshipView player, CommandEnum playa) {
			for (int i = 0; i < BattleshipView.SIZE; i++) {
				// get token and see if it's empty
				if (game.getGridToken(i, playa) == ShipEnum.empty) {
					player.clearIcon(i); // set icon to water
				} else {
					player.setShipIcon(i); // set icon to ship
				}
			}
		}

		/**
		 * Handles the placing ships role of the grid buttons.
		 * 
		 * @requires 0 <= 1 < 100
		 * @param player
		 *            the View instance whose grid the player is using to guess
		 * @param i
		 *            the position of the button the player has clicked
		 * @ensures places a ship in the model at position i or displays an
		 *          error message if ship couldn't be placed
		 */
		private void placeShips(BattleshipView player, int i) {
			// check requires
			assert i >= 0 && i < BattleshipView.SIZE : "i must be between 0 and 100";
			// needed constants
			final int three = 3, four = 4;
			// create a CommandEnum for player
			CommandEnum playa = null;
			if (player == player1) {
				playa = CommandEnum.player1;
			} else {
				playa = CommandEnum.player2;
			}

			// make the button's icon a ship
			player.setShipIcon(i);
			// attempt to add ship
			boolean error = game.addShip(i, direction, ship, playa);
			// if unsuccessful, display dialog that lets the user know
			if (error) {
				BattleshipView.displayPlacementError();
			} else {
				/*
				 * If successful, the grid listeners are currently not needed
				 * anymore. Make the ship buttons visible so that the player can
				 * select another ship to add.
				 */
				removeGridListeners(player);
				player.getShipButton(0).setVisible(true);
				player.getShipButton(1).setVisible(true);
				player.getShipButton(2).setVisible(true);
				player.getShipButton(three).setVisible(true);
				player.getShipButton(four).setVisible(true);
			}
			// update grids to show where a ship was added
			clearIcons(player, playa);
		}

		/**
		 * Handles the guessing role of the grid buttons.
		 * 
		 * @requires 0 <= i < 100
		 * @param player
		 *            the View instance whose grid the player is using to guess
		 * @param i
		 *            the position of the button the player has clicked
		 * @ensures a shot is fired at position i and indicates if it was a hit
		 *          or miss by changing the icon of the button. If the hit
		 *          results in a ship being destroyed, a message is displayed
		 *          informing the user
		 */
		private void guess(BattleshipView player, int i) {
			// check requires
			assert i >= 0 && i < BattleshipView.SIZE : "i must be between 0 and 100";
			/*
			 * If player is player 1, the other player is player 2 and vice
			 * versa. Create variable and CommandEnum for the other player.
			 */
			BattleshipView otherPlayer = null;
			CommandEnum otherPlaya = null;
			CommandEnum playa = null;
			if (player == player1) {
				otherPlayer = player2;
				otherPlaya = CommandEnum.player2;
				playa = CommandEnum.player1;
			} else {
				otherPlayer = player1;
				otherPlaya = CommandEnum.player1;
				playa = CommandEnum.player2;
			}

			// fireShot returns either a type of ship or empty
			ship = game.fireShot(i, otherPlaya);
			// disable grid button
			player.getGridButton(i).setEnabled(false);
			// if ship is not empty, there was a hit
			if (ship != ShipEnum.empty) {
				// change icon to ship
				player.setShipDisabledIcon(i);
				/*
				 * Check if ship has sunk and display dialog letting the player
				 * know.
				 */
				if (game.isShipSunk(ship, otherPlaya)) {
					BattleshipView.shipDestroyed(ship);
				}
			} 
			/*
			 * Remove player 1's grid listeners to prevent him/her from guessing
			 * again.
			 */
			removeGridListeners(player);
			/*
			 * If player 1 found all of player 2's ships, player 2 has lost.
			 * Clear player 2's grid to show player 1's remaining ships, and
			 * display dialog letting the players know of the winner.
			 */
			if (game.hasPlayerLost(otherPlaya)) {
				ShipEnum s1 = null;
				ShipEnum s2 = null;
				// show loser the other player's ships, clear away guesses
				for (int p = 0; p < BattleshipView.SIZE; p++) {
					s1 = game.getGridToken(p, playa);
					s2 = game.getGridToken(p, otherPlaya);
					if (s1 != ShipEnum.empty) {
						// change icon to ship
						otherPlayer.getGridButton(p).setEnabled(false);
						otherPlayer.setShipDisabledIcon(p);
					} else {
						otherPlayer.completeClearIcon(p);
						player.completeClearIcon(p);
					}
					if (s2 != ShipEnum.empty) {
						player.setShipDisabledIcon(p);
					}
				}
				if (player == player1) {
					BattleshipView.displayWinner(p1);
				} else {
					BattleshipView.displayWinner(p2);
				}
			} else { // player 2 hasn't lost. Let him/her guess.
				addGridListeners(otherPlayer);
			}
		}

		// actionPerformed method
		@Override
		public void actionPerformed(ActionEvent e) {
			// get command
			String command = e.getActionCommand();
			int i = new Integer(command.substring(1));
			if (command.charAt(0) == 'a') { // for player 1
				// if game has not started, the player is placing ships
				if (!game.hasGameStarted()) {
					placeShips(player1, i); // call place ships method
					/*
					 * If player 1 has added all the ships, the player turn
					 * switches to 2.
					 */
					if (game.getPlayerTurn() == CommandEnum.player2) {
						// clear all icons so that the ships are hidden
						player1.clearAll();
						// player 1's toolbar is no longer needed
						player1.deactivateToolbar();
						// display dialog asking player 2 to place ships
						BattleshipView.askPlayerToStart(p2);
						// add listeners to buttons needed for placing ships
						addShipListeners(player2);
						addOrientationListeners(player2);
						// set direction back to default (up)
						direction = CommandEnum.up;
					}
				} else { // the game has started and the player is guessing
					guess(player1, i); // call guess method
				}
			} else { // for player 2
				// if game has not started, the player is placing ships
				if (!game.hasGameStarted()) {
					placeShips(player2, i); // call placeShips method
					/*
					 * If player 2 has added all the ships, the player turn
					 * switches to 1 and the game starts.
					 */
					if (game.hasGameStarted()) {
						// clear all icons so that the ships are hidden
						player2.clearAll();
						// player 2's toolbar is no longer needed
						player2.deactivateToolbar();
						/*
						 * Add listeners to player 1's grid so he/she can begin
						 * guessing.
						 */
						addGridListeners(player1);
						// display dialog indicating the game as started
						BattleshipView.startGame();
					}
				} else { // the game has started and the player is guessing
					guess(player2, i); // call guess method
				}
			}
		}
	}

	// Main method
	/**
	 * Main method which simply calls the private constructor of this utility
	 * class in order to begin the program.
	 * 
	 * @param args
	 *            is empty
	 */
	public static void main(String[] args) {
		new BattleshipController();
	}
}
