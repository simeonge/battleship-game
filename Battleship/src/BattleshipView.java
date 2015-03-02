import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

/**
 * View component for a Battleship game. Provides a 10x10 grid for players and
 * the following buttons: up, down, left, right, Accept, Clear, Aircraft
 * Carrier, Battleship, Cruiser, Submarine, and Destroyer.
 * 
 * @convention 1 <= player <= 2
 * @correspondence grid corresponds to BattleshipModel's boardMap
 */
@SuppressWarnings("serial")
public final class BattleshipView extends JFrame {
	// ship buttons
	/**
	 * A button that displays "Aircraft Carrier".
	 */
	private JButton acBtn = new JButton("Aircraft Carrier");
	/**
	 * A button that displays "Battleship".
	 */
	private JButton bsBtn = new JButton("Battleship");
	/**
	 * A button that displays "Cruiser".
	 */
	private JButton cBtn = new JButton("Cruiser");
	/**
	 * A button that displays "Submarine".
	 */
	private JButton sBtn = new JButton("Submarine");
	/**
	 * A button that displays "Destroyer".
	 */
	private JButton dBtn = new JButton("Destroyer");
	// orientation buttons
	/**
	 * A radio button that displays "Up".
	 */
	private JRadioButton upBtn = new JRadioButton("Up");
	/**
	 * A radio button that displays "Down".
	 */
	private JRadioButton downBtn = new JRadioButton("Down");
	/**
	 * A radio button that displays "Left".
	 */
	private JRadioButton leftBtn = new JRadioButton("Left");
	/**
	 * A radio button that displays "Right".
	 */
	// toolbars
	private JRadioButton rightBtn = new JRadioButton("Right");
	/**
	 * A toolbar that contains the ship buttons.
	 */
	private JToolBar shipTool = new JToolBar();
	/**
	 * A toolbar that contains the orientation buttons.
	 */
	private JToolBar orientationTool = new JToolBar();
	/**
	 * A panel that contains the toolbars and a grid.
	 */
	private JPanel pane = new JPanel();
	/**
	 * Grid size.
	 */
	static final int SIZE = 100;
	/**
	 * Identifies player.
	 */
	private int player = 0;
	/**
	 * Represents a 10x10 grid of buttons.
	 */
	private JButton[] grid = new JButton[SIZE];
	/**
	 * Default images for grid buttons.
	 */
	private ImageIcon defaultImage = null, disabledImage = null;

	/**
	 * Adds labels and buttons to a panel with a grid layout.
	 * 
	 * @return a JPanel using GridLayout with 11 rows and 11 columns, <br />
	 *         each row in column 1 is labeled alphabetically, starting at row
	 *         2, <br />
	 *         each column in row 1 is labeled numerically, starting at column
	 *         2, <br />
	 *         and the remaining cells are made into buttons
	 */
	private JPanel populateGrid() {
		final int x = 11;
		final int y = 11;

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(x, y));
		// make first cell blank
		panel.add(new JLabel(""));
		// label the 10 columns
		panel.add(new JLabel("1"));
		panel.add(new JLabel("2"));
		panel.add(new JLabel("3"));
		panel.add(new JLabel("4"));
		panel.add(new JLabel("5"));
		panel.add(new JLabel("6"));
		panel.add(new JLabel("7"));
		panel.add(new JLabel("8"));
		panel.add(new JLabel("9"));
		panel.add(new JLabel("10"));

		int count = y + 1;
		int btnCount = 0;

		// constants for row label locations
		final int a = y + 1;
		final int b = a * 2;
		final int c = a * 3;
		final int d = a * 4;
		final int e = a * 5;
		final int f = a * 6;
		final int g = a * 7;
		final int h = a * 8;
		final int i = a * 9;
		final int j = a * 10;

		// add labels to each row in column 1 and
		// add buttons everywhere else
		while (count < (x * y) + y) {
			if (count % (x + 1) <= 1) {
				// add label to row
				switch (count) {
				case a:
					panel.add(new JLabel("A"));
					break;
				case b:
					panel.add(new JLabel("B"));
					break;
				case c:
					panel.add(new JLabel("C"));
					break;
				case d:
					panel.add(new JLabel("D"));
					break;
				case e:
					panel.add(new JLabel("E"));
					break;
				case f:
					panel.add(new JLabel("F"));
					break;
				case g:
					panel.add(new JLabel("G"));
					break;
				case h:
					panel.add(new JLabel("H"));
					break;
				case i:
					panel.add(new JLabel("I"));
					break;
				case j:
					panel.add(new JLabel("J"));
					break;
				default:
					break;
				}
			} else {
				// add a button
				JButton button = new JButton();
				panel.add(button);
				grid[btnCount] = button;
				grid[btnCount].setIcon(defaultImage);
				btnCount++;
			}
			count++;
		}
		return panel;
	}

	/**
	 * Initializes shipTool by adding all the ship buttons to it.
	 */
	private void buildShipToolbar() {

		TextField select = new TextField("Select ship: ");
		select.setEditable(false);
		shipTool.add(select);
		shipTool.add(this.acBtn);
		shipTool.add(this.bsBtn);
		shipTool.add(this.cBtn);
		shipTool.add(this.sBtn);
		shipTool.add(this.dBtn);
		shipTool.setEnabled(false);
	}

	/**
	 * Initializes orienationTool by adding all the orientation buttons to it.
	 * Also adds the orientation buttons to a button group and makes "Up" the
	 * default button.
	 */
	private void buildOrientationToolbar() {
		ButtonGroup orientation = new ButtonGroup();
		// make "Up" the default radio button
		upBtn.setSelected(true);
		orientation.add(this.upBtn);
		orientation.add(this.downBtn);
		orientation.add(this.leftBtn);
		orientation.add(this.rightBtn);
		TextField select = new TextField("Select orientation: ");
		select.setEditable(false);
		orientationTool.add(select);
		orientationTool.add(this.upBtn);
		orientationTool.add(this.downBtn);
		orientationTool.add(this.leftBtn);
		orientationTool.add(this.rightBtn);
		orientationTool.setEnabled(false);
	}

	/**
	 * Constructor.
	 * 
	 * @requires 1 <= player <= 2
	 * @param name
	 *            title of window
	 * @param player
	 *            either player 1 or player 2
	 */
	public BattleshipView(String name, int player) {
		super(name);
		assert player == 1 || player == 2 : "1 <= player <= 2";
		this.player = player;
		try {
			defaultImage = new ImageIcon(new URL(
					"http://a0.twimg.com/profile_images/"
							+ "77185680/Water1_normal.gif"));
			disabledImage = new ImageIcon(new URL("http://www.scalehobbyist.com/images/" 
					+	"catagorypics/battleship_3.jpg"));
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL.");
		}
		pane.setLayout(new BorderLayout());
		// set preferred size to half screen width and height
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.setSize(dim.width / 2, dim.height / 2);
		pane.setPreferredSize(dim);
		buildShipToolbar();
		pane.add(shipTool, BorderLayout.NORTH);
		buildOrientationToolbar();
		pane.add(orientationTool, BorderLayout.SOUTH);
		pane.add(populateGrid(), BorderLayout.CENTER);
		// finalize
		this.setContentPane(pane);
		this.pack();
	}

	/**
	 * Returns the radio button corresponding to i.
	 * 
	 * @requires 0 <= i <= 3
	 * @param i
	 *            number associated with a radio button
	 * @return upBtn if i = 0 <br />
	 *         downBtn if i = 1 <br />
	 *         leftBtn if i = 2 <br />
	 *         rightBtn if i = 3
	 */
	public JRadioButton getOrientationButton(int i) {
		// needed constants
		final int three = 3;
		assert 0 <= i && i <= three : "0 <= i <= 3";
		final int up = 0;
		final int down = 1;
		final int left = 2;
		final int right = 3;
		JRadioButton r = null;
		switch (i) {
		case up:
			r = upBtn;
			break;
		case down:
			r = downBtn;
			break;
		case left:
			r = leftBtn;
			break;
		case right:
			r = rightBtn;
			break;
		default:
			break;
		}
		return r;
	}

	/**
	 * Returns the ship button corresponding to i.
	 * 
	 * @requires 0 <= i <= 4
	 * @param i
	 *            ship button number
	 * @return acBtn if i = 0, <br />
	 *         bsBtn if i = 1, <br />
	 *         cBtn if i = 2, <br />
	 *         sBtn if i = 3, <br />
	 *         dBtn if i = 4
	 */
	public JButton getShipButton(int i) {
		// needed constants
		final int four = 4;
		assert 0 <= i && i <= four : "0 <= i <= 4";
		final int ac = 0;
		final int bs = 1;
		final int c = 2;
		final int s = 3;
		final int d = 4;

		JButton ship = null;
		switch (i) {
		case ac:
			ship = acBtn;
			break;
		case bs:
			ship = bsBtn;
			break;
		case c:
			ship = cBtn;
			break;
		case s:
			ship = sBtn;
			break;
		case d:
			ship = dBtn;
			break;
		default:
			break;
		}
		return ship;
	}

	/**
	 * Sets a button's icon to a ship.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param i
	 *            grid button index
	 */
	public void setShipIcon(int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		grid[i].setIcon(disabledImage);
	}

	/**
	 * Sets a button's disabled icon to a ship.
	 * @requires 0 <= i <= SIZE - 1
	 * @param i
	 *            grid button index
	 */
	public void setShipDisabledIcon(int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		grid[i].setIcon(disabledImage);
		grid[i].setDisabledIcon(disabledImage);
	}

	/**
	 * Sets a single button's icon to water.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param i
	 *            grid button index
	 */
	public void clearIcon(int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		grid[i].setIcon(defaultImage);
	}

	/**
	 * Sets a single button's icon and disabled icon to water.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param i
	 *            grid button index
	 */
	public void completeClearIcon(int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		grid[i].setIcon(defaultImage);
		grid[i].setDisabledIcon(defaultImage);
	}


	/**
	 * Sets every button in grid to display water.
	 */
	public void clearAll() {
		for (int i = 0; i < BattleshipView.SIZE; i++) {
			grid[i].setIcon(defaultImage);
		}
	}

	/**
	 * Removes shipTool and orientationTool from the pane.
	 */
	public void deactivateToolbar() {
		pane.remove(shipTool);
		pane.remove(orientationTool);
		this.setContentPane(pane);
	}

	/**
	 * Retrieves the grid button at i.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param i
	 *            grid button index
	 * @return button at location i of grid
	 */
	public JButton getGridButton(int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		return grid[i];
	}

	/**
	 * Adds an action listener to the button at location i of grid.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param bal
	 *            button action listener to be added
	 * @param i
	 *            grid button index
	 */
	public void addGridButtonListener(ActionListener bal, int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		String command = null;
		if (this.player == 1) {
			command = "a";
		} else {
			command = "b";
		}
		grid[i].addActionListener(bal);
		grid[i].setActionCommand(command.concat(Integer.toString(i)));
	}

	/**
	 * Removes the specified action listener from the button at location i of
	 * grid.
	 * 
	 * @requires 0 <= i <= SIZE - 1
	 * @param bal
	 *            button action listener to be removed
	 * @param i
	 *            grid button index
	 */
	public void removeGridButtonListener(ActionListener bal, int i) {
		assert 0 <= i && i <= (SIZE - 1) : "0 <= i <= SIZE - 1";
		grid[i].removeActionListener(bal);
	}

	/**
	 * Adds an action listener to the orientation button corresponding to i.
	 * 
	 * @requires 0 <= i <= 3
	 * @param ral
	 *            action listener to be added
	 * @param i
	 *            orientation button indicator
	 */
	public void addOrientationButtonListener(ActionListener ral, int i) {
		// needed constants
		final int three = 3;
		assert 0 <= i && i <= three : "0 <= i <= 3";
		String command = null;
		if (this.player == 1) {
			command = "a";
		} else {
			command = "b";
		}
		JRadioButton r = getOrientationButton(i);
		r.addActionListener(ral);
		r.setActionCommand(command.concat(Integer.toString(i)));
	}

	/**
	 * Adds an action listener to the ship button corresponding to i.
	 * 
	 * @requires 0 <= i <= 4
	 * @param bal
	 *            action listener to be added
	 * @param i
	 *            ship button indicator
	 */
	public void addShipButtonListener(ActionListener bal, int i) {
		// needed constants
		final int four = 4;
		assert 0 <= i && i <= four : "0 <= i <= 4";
		String command = null;
		if (this.player == 1) {
			command = "a";
		} else {
			command = "b";
		}
		JButton ship = getShipButton(i);
		ship.addActionListener(bal);
		ship.setActionCommand(command.concat(Integer.toString(i)));
	}

	/**
	 * Alerts user of a ship placement error.
	 */
	public static void displayPlacementError() {
		JOptionPane.showMessageDialog(new JPanel(), "Illegal ship placement!");
	}

	/**
	 * Asks player to place ships.
	 * 
	 * @param player
	 *            name of player
	 */
	public static void askPlayerToStart(String player) {
		String message = player + ": Deploy your ships.";
		JOptionPane.showMessageDialog(new JPanel(), message);
	}

	/**
	 * Displays winner.
	 * 
	 * @param player
	 *            name of player
	 */
	public static void displayWinner(String player) {
		String message = player + " won. Congratulations!";
		JOptionPane.showMessageDialog(new JPanel(), message);
	}

	/**
	 * Tells user what ship was destroyed.
	 * 
	 * @param ship
	 *            enumeration of ship
	 */
	public static void shipDestroyed(ShipEnum ship) {
		String message = ship + " destroyed!";
		JOptionPane.showMessageDialog(new JPanel(), message);
	}

	/**
	 * Tells the user the game has started.
	 */
	public static void startGame() {
		JOptionPane.showMessageDialog(new JPanel(), "The war has begun!");
	}

}
