package hanto.studentgrimshaw_mckenna.delta;

import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.factories.HantoGameFactory;

import org.junit.Before;
import org.junit.Test;

public class DeltaHantoGameTests {
	HantoTestGame testGame;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO);
	}

	@Test
	public void checkForBlueResignation() throws HantoException {
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test
	public void checkForRedResignation() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.BLUE_WINS, result);
	}
	
	@Test
	public void canPlaceCrab() throws HantoException {
		testGame.makeMove(HantoPieceType.CRAB, null, new ConcreteHantoCoordinate(0, 0));

	}

	@Test
	public void testMoreThan20Turn() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(-1, 2));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(-1, 1));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(-1, 1),
				new ConcreteHantoCoordinate(-2, 2));
		assertEquals(MoveResult.OK, result);

		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, -1));

		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void butterFlyMayWalkOneHex() throws HantoException {
		MoveResult result;
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, coord(0, 0), coord(1, 0));

		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void crabMayWalkOneHexOnturn() throws HantoException {
		MoveResult result;

		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(-1, 0));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(0, 2));
		result = testGame.makeMove(HantoPieceType.CRAB, coord(-1, 0), coord(-1, 1));

		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void crabMayNotWalkMoreThanOneHexOnTurn() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(-1, 0));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(0, 2));
		testGame.makeMove(HantoPieceType.CRAB, coord(-1, 0), coord(1, -1));
	}

	@Test
	public void checkSparrowCanFly() throws HantoException {

		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		testGame.makeMove(HantoPieceType.SPARROW, null, coord(-1, 0));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(0, 2));

		testGame.makeMove(HantoPieceType.SPARROW, coord(-1, 0), coord(0, 3));
	}

	@Test(expected = HantoException.class)
	public void checkFlyOntoPiece() throws HantoException {

		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		testGame.makeMove(HantoPieceType.SPARROW, null, coord(-1, 0));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(0, 2));

		testGame.makeMove(HantoPieceType.SPARROW, coord(-1, 0), coord(0, 2));
	}

	@Test(expected = HantoException.class)
	public void checkFlyBreaksChain() throws HantoException {

		testGame.makeMove(HantoPieceType.SPARROW, null, coord(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(0, 1));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, coord(-1, 0));
		testGame.makeMove(HantoPieceType.CRAB, null, coord(0, 2));

		testGame.makeMove(HantoPieceType.SPARROW, coord(0, 0), coord(0, 3));
	}

	@Test
	public void flyToWin() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(-1, 2));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(-1, 1));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(0, 3));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(0, 3),
				new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.BLUE_WINS, result);
	}

	@Test(expected = HantoException.class)
	public void attemptToPlaceAfterUsingAllSparrows() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[4];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));

		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(0);
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));

	}

	@Test(expected = HantoException.class)
	public void attemptToPlaceAfterUsingAllCrabs() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[4];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(0, 2));

		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(0);
		testGame.makeMove(HantoPieceType.CRAB, null, new ConcreteHantoCoordinate(1, 0));

	}

	@Test(expected = HantoException.class)
	public void attemptToPlaceAfterUsingAllButterflies() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[1];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		;

		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(0);
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));

	}
	
	@Test
	public void createDeltaHantoGame() throws HantoException{
		HantoGame game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.DELTA_HANTO);
		game.makeMove(HantoPieceType.BUTTERFLY, null, coord(0,0));
	}

	private ConcreteHantoCoordinate coord(int x, int y) {
		return new ConcreteHantoCoordinate(x, y);
	}
}
