/*
import static org.junit.Assert.*;
import org.junit.Test;


public class BattleshipTester {
	
	BattleshipGame testGame = new SolidBattleshipGame();
	
	@Test 
	void addShip_test_addShip_sucess_1(){
		
		boolean destroyerAdd = testGame.addShip(00, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Checking Destroyer token is on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));
	}

	@Test
	void addShip_test_addShip_sucess_2(){

		boolean destroyerAdd = testGame.addShip(00, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Checking Destroyer token is on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));

		boolean submarineAdd = testGame.addShip(11, CommandEnum.down, ShipEnum.Submarine, CommandEnum.player1);
		assertEquals("Attempted to add Submarine token to the map", true, submarineAdd);
		assertEquals("Checking Submarine token is on the map", false, testGame.isShipSunk(ShipEnum.Submarine, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_sucess_3(){
		
		boolean destroyerAdd = testGame.addShip(00, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Checking Destroyer token is on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));

		boolean submarineAdd = testGame.addShip(11, CommandEnum.down, ShipEnum.Submarine, CommandEnum.player1);
		assertEquals("Attempted to add Submarine token to the map", true, submarineAdd);
		assertEquals("Checking Submarine token is on the map", false, testGame.isShipSunk(ShipEnum.Submarine, CommandEnum.player1));
		
		boolean cruiserAdd = testGame.addShip(22, CommandEnum.down, ShipEnum.Cruiser, CommandEnum.player1);
		assertEquals("Attempted to add Cruiser token to the map", true, cruiserAdd);
		assertEquals("Checking Cruiser token is on the map", false, testGame.isShipSunk(ShipEnum.Cruiser, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_sucess_4(){
		
		boolean destroyerAdd = testGame.addShip(00, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Checking Destroyer token is on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));

		boolean submarineAdd = testGame.addShip(11, CommandEnum.down, ShipEnum.Submarine, CommandEnum.player1);
		assertEquals("Attempted to add Submarine token to the map", true, submarineAdd);
		assertEquals("Checking Submarine token is on the map", false, testGame.isShipSunk(ShipEnum.Submarine, CommandEnum.player1));
		
		boolean cruiserAdd = testGame.addShip(22, CommandEnum.down, ShipEnum.Cruiser, CommandEnum.player1);
		assertEquals("Attempted to add Cruiser token to the map", true, cruiserAdd);
		assertEquals("Checking Cruiser token is on the map", false, testGame.isShipSunk(ShipEnum.Cruiser, CommandEnum.player1));

		boolean battleshipAdd = testGame.addShip(33, CommandEnum.down, ShipEnum.Battleship, CommandEnum.player1);
		assertEquals("Attempted to add Battleship token to the map", true, battleshipAdd);
		assertEquals("Checking Battleship token is on the map", false, testGame.isShipSunk(ShipEnum.Battleship, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_sucess_5(){
		
		boolean destroyerAdd = testGame.addShip(00, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Checking Destroyer token is on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));

		boolean submarineAdd = testGame.addShip(11, CommandEnum.down, ShipEnum.Submarine, CommandEnum.player1);
		assertEquals("Attempted to add Submarine token to the map", true, submarineAdd);
		assertEquals("Checking Submarine token is on the map", false, testGame.isShipSunk(ShipEnum.Submarine, CommandEnum.player1));
		
		boolean cruiserAdd = testGame.addShip(22, CommandEnum.down, ShipEnum.Cruiser, CommandEnum.player1);
		assertEquals("Attempted to add Cruiser token to the map", true, cruiserAdd);
		assertEquals("Checking Cruiser token is on the map", false, testGame.isShipSunk(ShipEnum.Cruiser, CommandEnum.player1));

		boolean battleshipAdd = testGame.addShip(33, CommandEnum.down, ShipEnum.Battleship, CommandEnum.player1);
		assertEquals("Attempted to add Battleship token to the map", true, battleshipAdd);
		assertEquals("Checking Battleship token is on the map", false, testGame.isShipSunk(ShipEnum.Battleship, CommandEnum.player1));

		boolean carrierAdd = testGame.addShip(44, CommandEnum.down, ShipEnum.Aircraft_Carrier, CommandEnum.player1);
		assertEquals("Attempted to add Carrier token to the map", true, carrierAdd);
		assertEquals("Checking Carrier token is on the map", false, testGame.isShipSunk(ShipEnum.Aircraft_Carrier, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_failure_1(){
		
		boolean destroyerAdd = testGame.addShip(00, CommandEnum.up, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", false, destroyerAdd);
		assertEquals("Ensuring Destroyer token is not on the map", true, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_failure_2(){

		boolean destroyerAdd = testGame.addShip(55, CommandEnum.up, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Ensuring Destroyer token is not on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));
		
		boolean carrierAdd = testGame.addShip(55, CommandEnum.left, ShipEnum.Aircraft_Carrier, CommandEnum.player1);
		assertEquals("Attempted to add Carrier token to the map", false, carrierAdd);
		assertEquals("Checking Carrier token is on the map", true, testGame.isShipSunk(ShipEnum.Aircraft_Carrier, CommandEnum.player1));
	}
	
	@Test
	void addShip_test_addShip_failure_3(){

		boolean destroyerAdd = testGame.addShip(0, CommandEnum.down, ShipEnum.Destroyer, CommandEnum.player1);
		assertEquals("Attempted to add Destroyer token to the map", true, destroyerAdd);
		assertEquals("Ensuring Destroyer token is not on the map", false, testGame.isShipSunk(ShipEnum.Destroyer, CommandEnum.player1));
		
		boolean carrierAdd = testGame.addShip(60, CommandEnum.up, ShipEnum.Aircraft_Carrier, CommandEnum.player1);
		assertEquals("Attempted to add Carrier token to the map", false, carrierAdd);
		assertEquals("Checking Carrier token is on the map", true, testGame.isShipSunk(ShipEnum.Aircraft_Carrier, CommandEnum.player1));
	}
	
	
}*/