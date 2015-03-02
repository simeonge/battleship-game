/**
 * This interface represents the model of the Battleship game. It has two maps
 * with positions 0 to 99, at which players can place ships and fire shots.
 * 
 * @mathmodel playerMaps is a map of players mapped on position player is a
 *            token that identifies a player. <br />
 *            position is a map position of columns A-J concatenated with
 *            numbers 1-10. Example: C5 <br />
 *            mapReference can be a token called hit, empty, miss, or ship. <br />
 *            ship is of type Destroyer, Cruiser, Battleship, Carrier, or
 *            Submarine <br />
 *            Destroyer takes up 2 adjacent boardMap positions. <br />
 *            Cruiser takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Submarine takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Battleship takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            Carrier takes up 3 adjacent boardMap positions, in a linear
 *            fashion. <br />
 *            ship_sunk token is one token called one of the following:
 *            destroyer_sunk, cruiser_sunk, submarine_sunk, carrier_sunk,
 *            battleship_sunk, and carrier_sunk. <br />
 *            carrier_sunk means that the carrier has no remaining tokens left
 *            on boardMap.<br />
 *            battleship_sunk means that the battleship has no remaining tokens
 *            left on boardMap.<br />
 *            submarine_sunk means that the submarine has no remaining tokens
 *            left on boardMap.<br />
 *            cruiser_sunk means that the cruiser has no remaining tokens left
 *            on boardMap.<br />
 *            destroyer_sunk means that the destroyer has no remaining tokens
 *            left on boardMap.<br />
 * 
 * @constraint Ships may not overlap, and must fit entirely on map. There are
 *             only two maps and only two players. The ships may not take up
 *             more positions than described in the math model.
 * 
 * @initially All positions on boardMap are set to empty for both players, the
 *            default ship size is set, it is player 1's turn, and both player's
 *            grid are in playerMap.
 */
public interface BattleshipGame {

	/**
	 * This method attempts to add in a single ship onto the map. The method
	 * returns true if the ship was added successfully, or false if there was an
	 * error during the add process. If an error is returned, the map is,
	 * unchanged.
	 * 
	 * @param mapIndex
	 *            the map index that the ship will first attempt to be added
	 * @param placeDirection
	 *            the direction that the ship will be placed on the map
	 * @param shipType
	 *            the ship that will be placed
	 * @param player
	 *            the player that is trying to place a ship on his map
	 * @ensures the ship was successfully placed, or the map remains unchanged.
	 *          Also, the current player is updated to the next player.
	 * 
	 * @return Returns false if and only if the ship was placed successfully
	 *         with no errors. Returns true if the ship could not be placed.
	 */
	boolean addShip(int mapIndex, CommandEnum placeDirection,
			ShipEnum shipType, CommandEnum player);

	/**
	 * This method attempts to fire a shot at the corresponding map index.
	 * 
	 * @param mapIndex
	 *            the index on the map that the player is attempting to fire a
	 *            shot at on boardMap.
	 * @param player
	 *            the player the shot is being fired at
	 * @return -Returns a ship_sunk token corresponding to the ship hit if and
	 *         only if a ship token was replaced by a hit token and the ship has
	 *         no remaining tokens on boardMap. <br />
	 *         -Returns a hit token if and only if a ship token was at mapIndex,
	 *         and the ship still has tokens left on boardMap. <br />
	 *         -Returns an error token if mapIndex already contains a hit or a
	 *         miss token. <br />
	 *         -Returns a miss token if an empty token at mapIndex was replaced
	 *         with a miss token.
	 * @ensures A hit token is placed at mapIndex if and only if a ship token
	 *          was at mapIndex, or a miss token is placed at mapIndex if and
	 *          only if an empty token was at mapIndex. Otherwise, boardMap
	 *          remains unchanged. Also, the current player is updated to the
	 *          next player.
	 */
	ShipEnum fireShot(int mapIndex, CommandEnum player);

	/**
	 * Returns the player who is currently active.
	 * 
	 * @return Returns the active player's token.
	 */
	CommandEnum getPlayerTurn();

	/**
	 * Returns true if the ship has sunk.
	 * 
	 * @param ship
	 *            the ship whose status is checked
	 * @param player
	 *            the player to whom the ship belongs
	 * @return true if and only if the ship is no longer in the map
	 */
	boolean isShipSunk(ShipEnum ship, CommandEnum player);

	/**
	 * Returns the token at position gridIndex in player's map.
	 * 
	 * @param gridIndex
	 *            the position on the map whose ShipEnum token we want
	 * @param player
	 *            the player whose map we're searching
	 * @return a ShipEnum token that is either a type of ship, hit, or empty
	 */
	ShipEnum getGridToken(Integer gridIndex, CommandEnum player);

	/**
	 * Returns true if player has no more ships left on map.
	 * 
	 * @param player
	 *            whose status is checked
	 * @return true if and only if player has no more ships left on map
	 */
	boolean hasPlayerLost(CommandEnum player);

	/**
	 * Returns true if game has started.
	 * 
	 * @return true if and only if game has started
	 */
	boolean hasGameStarted();
}
