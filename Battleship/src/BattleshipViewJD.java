import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

/**
 * View component for a Battleship game. Provides a 10x10 grid for players and
 * the following buttons: up, down, left, right, Accept, Clear, Aircraft
 * Carrier, Battleship, Cruiser, Submarine, and Destroyer
 */
public class BattleshipViewJD extends JFrame {

	private JButton acceptBtn = new JButton("Accept");
	private JButton clearBtn = new JButton("Clear");
	private JButton ACBtn = new JButton("Aircraft Carrier");
	private JButton BSBtn = new JButton("Battleship");
	private JButton CBtn = new JButton("Cruiser");
	private JButton SBtn = new JButton("Submarine");
	private JButton DBtn = new JButton("Destroyer");
	private JRadioButton upBtn = new JRadioButton("Up");
	private JRadioButton downBtn = new JRadioButton("Down");
	private JRadioButton leftBtn = new JRadioButton("Left");
	private JRadioButton rightBtn = new JRadioButton("Right");
	static final int size = 100;
	private int player = 0;
	private JButton[] grid = new JButton[size];
	private ActionListener[] buttonListeners = new ActionListener[size];
	private ImageIcon defaultImage = null, DisabledImage = null;

	/**
	 * Adds labels and buttons to a panel with a grid layout.
	 * 
	 * @return a JPanel using GridLayout with x number of rows, <br />
	 *         y number of columns, <br />
	 *         each row in column 1 is labeled alphabetically, starting at row
	 *         2, <br />
	 *         each column in row 1 is labeled numerically, starting at column
	 *         2, <br />
	 *         and the remaining cells are made into buttons
	 */
	private JPanel populateGrid(JButton[] grid) {
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
		while (count < (x * y) + y) {
			if (count % (x + 1) <= 1) {
				// add label to row
				switch (count) {
				case 12:
					panel.add(new JLabel("A"));
					break;
				case 12 * 2:
					panel.add(new JLabel("B"));
					break;
				case 12 * 3:
					panel.add(new JLabel("C"));
					break;
				case 12 * 4:
					panel.add(new JLabel("D"));
					break;
				case 12 * 5:
					panel.add(new JLabel("E"));
					break;
				case 12 * 6:
					panel.add(new JLabel("F"));
					break;
				case 12 * 7:
					panel.add(new JLabel("G"));
					break;
				case 12 * 8:
					panel.add(new JLabel("H"));
					break;
				case 12 * 9:
					panel.add(new JLabel("I"));
					break;
				case 12 * 10:
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
				grid[btnCount].setDisabledIcon(DisabledImage);
				btnCount++;
			}
			count++;
		}
		return panel;
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
	public BattleshipViewJD(String name, int player) {
		super(name);
		this.player = player;
		try {
			defaultImage = new ImageIcon(
					new URL(
							"http://a0.twimg.com/profile_images/77185680/Water1_normal.gif"));
			DisabledImage = new ImageIcon(
					new URL(
							"http://www.scalehobbyist.com/images/catagorypics/battleship_3.jpg"));
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL.");
		}

		// make a toolbar
		JToolBar tools = new JToolBar();
		ButtonGroup orientation = new ButtonGroup();
		// make "Up" the default radio button
		upBtn.setSelected(true);
		orientation.add(this.upBtn);
		orientation.add(this.downBtn);
		orientation.add(this.leftBtn);
		orientation.add(this.rightBtn);
		tools.add(this.ACBtn);
		tools.add(this.BSBtn);
		tools.add(this.CBtn);
		tools.add(this.SBtn);
		tools.add(this.DBtn);
		tools.add(this.upBtn);
		tools.add(this.downBtn);
		tools.add(this.leftBtn);
		tools.add(this.rightBtn);
		tools.add(this.acceptBtn);
		tools.add(this.clearBtn);
		tools.setEnabled(false);
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		// set preferred size to half screen width and height
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dim.setSize(dim.width / 2, dim.height / 2);
		pane.setPreferredSize(dim);
		pane.add(tools, BorderLayout.PAGE_END);
		pane.add(populateGrid(grid), BorderLayout.CENTER);
//		pane.remove(tools);
		
		this.setContentPane(pane);
		this.pack();
	}

	public JButton getButton(int i) {
		return grid[i];
	}

	public void addButtonListener(ActionListener bal, int i) {
		String command = null;
		if (this.player == 1) {
			command = "a";
		} else {
			command = "b";
		}
		buttonListeners[i] = bal;
		grid[i].addActionListener(bal);
		grid[i].setActionCommand(command.concat(Integer.toString(i)));
	}

	public static void main(String[] args) {
		BattleshipViewJD test1 = new BattleshipViewJD("Battleship - Player 1",
				1);
		test1.setVisible(true);
		BattleshipViewJD test2 = new BattleshipViewJD("Battleship - Player 2",
				2);
		test2.setVisible(true);
		test1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
