import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the BattleshipGame interface and provides two maps
 * which are instances of BattleShipModel, one for each player. It also provides
 * two players who can place ships on their own grid and also fire shots on the
 * other player's grid.
 * 
 * @convention this has two players, each with a BattleShipModel which is a map
 *             with 100 positions
 * @correspondence there are two players who have maps, each with 100 positions
 */
public final class SolidBattleshipGame implements BattleshipGame {
	// Fields
	/**
	 * Boolean for determining if the game has started or not.
	 */
	private boolean gameStarted = false;

	/**
	 * Both players' grid maps from BattleShipModel.
	 */
	private Map<CommandEnum, BattleShipModel> playerMap =
			new HashMap<CommandEnum, BattleShipModel>();

	/**
	 * The current player turn.
	 */
	private CommandEnum playerTurn;

	/**
	 * For keeping track of the total number of blocks occupied in the map by
	 * ships. Should be 17 after all ships are places.
	 */
	private int blocksOccupied;

	// Constructor
	/**
	 * Constructor.
	 * 
	 * @initially All positions on boardMap are set to empty for both players,
	 *            the default ship size is set, it is player 1's turn, and both
	 *            player's grid are in playerMap.
	 */
	SolidBattleshipGame() {
		// initially it is player 1's turn
		playerTurn = CommandEnum.player1;
		// create two instances of boardMap
		BattleShipModel player1Board = new SolidBattleShipModel();
		BattleShipModel player2Board = new SolidBattleShipModel();
		// put the instances in playerMap
		playerMap.put(CommandEnum.player1, player1Board);
		playerMap.put(CommandEnum.player2, player2Board);
	}

	// Methods
	@Override
	public boolean addShip(int mapIndex, CommandEnum placeDirection,
			ShipEnum shipType, CommandEnum player) {
		// needed constants
		final int total = 17, desSize = 2, subSize = 3, cruSize = 3,
				batSize = 4, airSize = 5;
		// gives true if there was an error when placing the ship
		boolean answer = playerMap.get(player).addShip(mapIndex,
				placeDirection, shipType);
		/*
		 * If no error, map has been changed and blocksOccupied must be updated
		 * to reflect the number of ship tokens that were added.
		 */
		if (!answer) {
			switch (shipType) {
			case Aircraft_Carrier:
				blocksOccupied = blocksOccupied + airSize;
				break;
			case Battleship:
				blocksOccupied = blocksOccupied + batSize;
				break;
			case Cruiser:
				blocksOccupied = blocksOccupied + cruSize;
				break;
			case Submarine:
				blocksOccupied = blocksOccupied + subSize;
				break;
			case Destroyer:
				blocksOccupied = blocksOccupied + desSize;
			default:
				break;
			}
			/*
			 * If 17 ship tokens have been added and it is player 1's turn,
			 * player 2 must place his/her ships.
			 */
			if (blocksOccupied == total && playerTurn == CommandEnum.player1) {
				blocksOccupied = 0;
				playerTurn = CommandEnum.player2;
			} else if (blocksOccupied == total) {
				// otherwise, the game starts
				playerTurn = CommandEnum.player1;
				gameStarted = true;
			}
		}
		// returns true if there was an error
		return answer;
	}

	@Override
	public ShipEnum fireShot(int mapIndex, CommandEnum player) {
		// fire shot on player's map
		ShipEnum answer = playerMap.get(player).fireShot(mapIndex);
		// switch turns
		if (playerTurn == CommandEnum.player1) {
			playerTurn = CommandEnum.player2;
		} else {
			playerTurn = CommandEnum.player1;
		}
		/*
		 * Return the token returned from fireShot (either a type of ship, or
		 * empty).
		 */
		return answer;
	}

	@Override
	public CommandEnum getPlayerTurn() {
		// return playTurn
		return playerTurn;
	}

	@Override
	public boolean isShipSunk(ShipEnum ship, CommandEnum player) {
		// call player's isShipSunk
		return (playerMap.get(player).isShipSunk(ship));
	}

	@Override
	public ShipEnum getGridToken(Integer gridIndex, CommandEnum player) {
		// get grid token (either a type of ship, empty, or hit
		ShipEnum tempToken = playerMap.get(player).getGridToken(gridIndex);
		// return grid token
		return tempToken;
	}

	@Override
	public boolean hasPlayerLost(CommandEnum player) {
		// call player's loseCheck
		return playerMap.get(player).loseCheck();
	}

	@Override
	public boolean hasGameStarted() {
		// return gameStarted
		return gameStarted;
	}
}
