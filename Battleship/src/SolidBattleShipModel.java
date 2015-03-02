import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the BattleShipModel interface, and it has a map with
 * 100 positions, initially empty, where ships can be placed or shots can be
 * fired at.
 * 
 * @convention boardMap has 100 positions
 * 
 * @correspondence boardMap is a map with 100 positions
 */
public final class SolidBattleShipModel implements BattleShipModel {
	// Fields
	/**
	 * Tracks the number of each ship token left on boardMap.
	 */
	private Map<ShipEnum, Integer> hitTracker = new HashMap<ShipEnum, Integer>();

	/**
	 * Tracks the size of each ship.
	 */
	private final Map<ShipEnum, Integer> shipTracker;

	/**
	 * The map that the grid is stored on.
	 */
	private Map<Integer, ShipEnum> boardMap = new HashMap<Integer, ShipEnum>();

	/**
	 * Sets the lower bound of boardMap's grid.
	 */
	private static final int MAPMIN = 0;

	/**
	 * Sets the upper bound of boardMap's grid.
	 */
	private static final int MAPMAX = 99;

	// Constructor
	/**
	 * Constructor.
	 * 
	 * @initially sets every grid in boardMap to the empty, and sets the size of
	 *            each ship.
	 */
	public SolidBattleShipModel() {
		// needed constants
		final int mapSize = 100, desSize = 2, subSize = 3,
				cruSize = 3, batSize = 4, airSize = 5;
		// fill the map with empty tokens
		for (int i = 0; i < mapSize; i++) {
			boardMap.put(i, ShipEnum.empty);
		}
		// fill hitTracker map with ships and their sizes
		hitTracker.put(ShipEnum.Destroyer, desSize);
		hitTracker.put(ShipEnum.Submarine, subSize);
		hitTracker.put(ShipEnum.Cruiser, cruSize);
		hitTracker.put(ShipEnum.Battleship, batSize);
		hitTracker.put(ShipEnum.Aircraft_Carrier, airSize);
		/*
		 * Copy of hitTracker used to keep track of the number of grid positions
		 * a ship occupies when adding ships.
		 */
		shipTracker = new HashMap<ShipEnum, Integer>(hitTracker);
	}

	// Methods
	@Override
	public boolean addShip(int mapIndex, CommandEnum placeDirection,
			ShipEnum shipType) {
		// will return true of there was an error
		boolean addError = false;
		// get the number of positions shipType occupies
		int shipIndex = shipTracker.get(shipType);
		// create a copy of boardMap
		Map<Integer, ShipEnum> boardMapCopy = new HashMap<Integer, ShipEnum>(
				boardMap);
		// iterate shipIndex times
		while (shipIndex > 0) {
			// needed constants
			final int ten = 10;
			// we can only add ship if board is empty at mapIndex
			if (boardMapCopy.get(mapIndex) == ShipEnum.empty) {
				boardMapCopy.put(mapIndex, shipType); // add ship token
			} else { // there was an error
				addError = true;
				break;
			}
			// error if mapIndex goes over 0 or 99 while adding the ship
			if (mapIndex > MAPMAX || mapIndex < MAPMIN) {
				addError = true;
				break;
			}
			/*
			 * Update mapIndex to determine where ship will be added next based
			 * on placeDirection.
			 */
			switch (placeDirection) {
			case up:
				mapIndex = mapIndex - ten;
				break;
			case down:
				mapIndex = mapIndex + ten;
				break;
			case left:
				mapIndex = mapIndex - 1;
				// make sure we don't go into a different row
				int mapIndexCoy = mapIndex;
				if ((mapIndexCoy + 1) % ten == 0 && shipIndex != 1) {
					addError = true;
					break;
				}
				break;
			case right:
				mapIndex = mapIndex + 1;
				// make sure we don't go into a different row
				if (mapIndex % ten == 0 && shipIndex != 1) {
					addError = true;
					break;
				}
				break;
			default:
				break;
			}
			shipIndex--; // one token of the ship has been added
		}
		// update boardMap only if there were no errors
		if (!addError) {
			boardMap = boardMapCopy;
		}
		// return true if there was an error
		return addError;
	}

	@Override
	public ShipEnum fireShot(int mapIndex) {
		// get token at mapIndex in boardMap
		ShipEnum shipEnumReturn = boardMap.get(mapIndex);
		// if it's not empty, there's a ship. update hitTracker
		if (shipEnumReturn != ShipEnum.empty) {
			int count = hitTracker.get(shipEnumReturn);
			count--; // one token of ship has been destroyed
			hitTracker.put(shipEnumReturn, count);
			// put hit in boardMap
			boardMap.put(mapIndex, ShipEnum.hit);
		}
		/*
		 * Return empty if there's no ship at mapIndex and boardMap will be
		 * unchanged.
		 */
		return shipEnumReturn;
	}

	@Override
	public boolean loseCheck() {
		/*
		 * Check if player has lost all ships. The ships in hitTracker must all
		 * have a size of 0.
		 */
		boolean lossResult = false;
		if (hitTracker.get(ShipEnum.Destroyer) == 0
				&& hitTracker.get(ShipEnum.Submarine) == 0
				&& hitTracker.get(ShipEnum.Cruiser) == 0
				&& hitTracker.get(ShipEnum.Battleship) == 0
				&& hitTracker.get(ShipEnum.Aircraft_Carrier) == 0) {
			lossResult = true;
		}
		// return true if all ships are gone
		return lossResult;
	}

	@Override
	public boolean isShipSunk(ShipEnum ship) {
		boolean answer = false;
		// if ship is sunk, ship will have 0 tokens in hitTracker
		if (hitTracker.get(ship) == 0) {
			answer = true;
		}
		return answer;
	}

	@Override
	public ShipEnum getGridToken(Integer gridIndex) {
		/*
		 * Return whatever is in map at position gridIndex (either a ship or
		 * empty or hit).
		 */
		return boardMap.get(gridIndex);
	}
}
