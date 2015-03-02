/**
 * Interface for the model which has a map of 100 positions on which ships can
 * be placed.
 * 
 * @mathmodel boardMap is a map of positions mapped on mapReferences position is
 *            a map position of columns A-J concatenated with numbers 1-10,
 *            sorted as a grid. Example: C5 <br />
 *            mapReference can be a token called hit, empty, or ship. <br />
 *            ship is a token of type Destroyer, Cruiser, Battleship, Carrier,
 *            or Submarine <br />
 *            Destroyer takes up 2 adjacent boardMap positions. <br />
 *            Cruiser takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Submarine takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Battleship takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Carrier takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 * 
 * @constraint Ships may not overlap, and must fit entirely on map and the ships
 *             may not take up more positions than described in the math model.
 * 
 * @initially all positions on boardMap are set to empty.
 */

public interface BattleShipModel {

	/**
	 * This method attempts to add in a single ship onto the map. The method
	 * returns true if the ship could not be added successfully, false
	 * otherwise. If an error is returned, the map is unchanged.
	 * 
	 * @param mapIndex
	 *            the map index that the ship will first attempt to be added.
	 * @param placeDirection
	 *            the direction that the ship will be placed on the map.
	 * @param shipType
	 *            the ship that will be placed.
	 * @ensures the ship was successfully placed, or the map remains unchanged.
	 * 
	 * @return Returns false if and only if the ship was placed successfully
	 *         with no errors. Returns true if the ship could not be placed.
	 */
	boolean addShip(int mapIndex, CommandEnum placeDirection, ShipEnum shipType);

	/**
	 * This method attempts to fire a shot at the corresponding map index.
	 * 
	 * @param mapIndex
	 *            This is the index on the map that the player is attempting to
	 *            fire a shot at on boardMap.
	 * @return if there was a hit returns the type of ship that was hit,
	 *         otherwise returns empty
	 * 
	 * @requires 0 <= mapIndex < 100
	 * @ensures A hit token is placed at mapIndex if and only if a ship token
	 *          was at mapIndex Otherwise, boardMap remains unchanged.
	 */
	ShipEnum fireShot(int mapIndex);

	/**
	 * This method checks to see if the player on this map has lost the game or
	 * not.
	 * 
	 * @return true if there are no more ships on the map
	 */
	boolean loseCheck();

	/**
	 * Checks to see if there is any of the specified ship tokens on the map.
	 * 
	 * @param ship
	 *            The ship token that is to be checked.
	 * @return true if there are no more tokens of ship on the map
	 */
	boolean isShipSunk(ShipEnum ship);

	/**
	 * Returns the token that is mapped on boardMap.
	 * 
	 * @param gridIndex
	 *            The position to get the token from on boardMap.
	 * @return the ShipEnum at position gridIndex - either a type of ship, hit,
	 *         or empty
	 */
	ShipEnum getGridToken(Integer gridIndex);
}
