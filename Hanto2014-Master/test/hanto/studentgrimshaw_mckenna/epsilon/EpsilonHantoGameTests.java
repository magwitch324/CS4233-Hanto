package hanto.studentgrimshaw_mckenna.epsilon;

import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.HexCoordinateDirections;

import org.junit.Before;
import org.junit.Test;

public class EpsilonHantoGameTests {
	HantoTestGame testGame;

	@Before
	public void setUp() throws Exception {
		testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO);
	}

	@Test(expected = HantoException.class)
	public void cantReachDestination() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 5));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(0, 6));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(0, 7));
		assertEquals(MoveResult.BLUE_WINS, result);
	}

	@Test
	public void checkJumpCanReachDestination() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 5));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(0, 6));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.HORSE, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(0, 7));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void checkJumpCantReachDestination() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 5));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(0, 6));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.HORSE, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(1, 6));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void checkJumpCantReachStraightNonConnectedCoord() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(0, 5));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.HORSE, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(0, 6));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoPrematureResignationException.class)
	public void prematureResignationHandTest() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[7];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, coord(-5, -5));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test(expected = HantoPrematureResignationException.class)
	public void prematureResignationJumpTest() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[15];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(2, 0));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(3, 0));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(4, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(5, 0));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(6, 0));
		initialPieces[7] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(7, 0));
		initialPieces[8] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(8, 0));
		initialPieces[9] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(9, 0));
		initialPieces[10] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(10, 0));
		initialPieces[11] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(11, 0));
		initialPieces[12] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(13, 0));
		initialPieces[13] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(-1, 0));
		initialPieces[14] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(12, 0));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test(expected = HantoPrematureResignationException.class)
	public void prematureResignationTest() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 2));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(0, 3));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(0, 4));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test(expected = HantoPrematureResignationException.class)
	public void prematureResignationTestWalking() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[15];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(-1, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(2, 0));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(3, 0));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(4, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(5, 0));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(6, 0));
		initialPieces[7] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(7, 0));
		initialPieces[8] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(8, 0));
		initialPieces[9] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(9, 0));
		initialPieces[10] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(10, 0));
		initialPieces[11] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(11, 0));
		initialPieces[12] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(12, 0));
		initialPieces[13] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[14] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(13, 0));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test(expected = HantoPrematureResignationException.class)
	public void prematureResignationTestFlying() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[15];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(-1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(2, 0));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(3, 0));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(4, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(5, 0));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(6, 0));
		initialPieces[7] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(7, 0));
		initialPieces[8] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(8, 0));
		initialPieces[9] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(9, 0));
		initialPieces[10] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(10, 0));
		initialPieces[11] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(11, 0));
		initialPieces[12] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(12, 0));
		initialPieces[13] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[14] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(13, 0));

		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test
	public void resignationTest() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[15];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(1, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(2, 0));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(3, 0));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(4, 0));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(5, 0));
		initialPieces[6] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(6, 0));
		initialPieces[7] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(7, 0));
		initialPieces[8] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(8, 0));
		initialPieces[9] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(9, 0));
		initialPieces[10] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(10, 0));
		initialPieces[11] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(11, 0));
		initialPieces[12] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.HORSE,
				new ConcreteHantoCoordinate(12, 0));
		initialPieces[13] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(-1, 0));
		initialPieces[14] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(13, 0));

		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, result);
	}

	@Test(expected = HantoException.class)
	public void sparrowMoveTest() throws HantoException {
		PieceLocationPair[] initialPieces = new PieceLocationPair[6];
		initialPieces[0] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(0, -1));
		initialPieces[1] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 0));
		initialPieces[2] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY,
				new ConcreteHantoCoordinate(0, 1));
		initialPieces[3] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(1, 1));
		initialPieces[4] = new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.CRAB,
				new ConcreteHantoCoordinate(2, 1));
		initialPieces[5] = new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW,
				new ConcreteHantoCoordinate(2, 2));
		testGame.initializeBoard(initialPieces);
		testGame.setPlayerMoving(HantoPlayerColor.RED);
		testGame.setTurnNumber(20);
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(2, 2),
				new ConcreteHantoCoordinate(0, -2));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void enumTest() {
		HexCoordinateDirections.valueOf("UP");
	}

	private ConcreteHantoCoordinate coord(int x, int y) {
		return new ConcreteHantoCoordinate(x, y);
	}
}