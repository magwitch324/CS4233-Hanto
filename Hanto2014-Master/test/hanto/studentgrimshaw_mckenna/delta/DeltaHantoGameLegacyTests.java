package hanto.studentgrimshaw_mckenna.delta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.HantoPlayerColor.RED;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
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

public class DeltaHantoGameLegacyTests {

	HantoTestGame testGame;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO);
	}

	@Test
	public void gameNotNull() {
		assertNotNull(testGame);
	}

	@Test
	public void bluePlacesFirst() {
		assertEquals(HantoPlayerColor.BLUE, ((DeltaHantoGame) testGame).getActivePlayer().getColor());
	}

	@Test
	public void canPlaceAt00() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void redPlacesSecond() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(HantoPlayerColor.RED, ((DeltaHantoGame) testGame).getActivePlayer().getColor());
	}

	@Test(expected = HantoException.class)
	public void firstMoveNotAt00() throws HantoException {
		MoveResult result;
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(5, 0));

		assertEquals(MoveResult.OK, result);

	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceOnTopOfOtherPiece() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
	}

	@Test
	public void canPlacePieceNextToExistingPiece() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceNotNextToExistingPiece() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 1));
	}

	@Test
	public void getPieceAtTest() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		HantoPiece testPiece = testGame.getPieceAt(new ConcreteHantoCoordinate(0, 0));

		assertEquals(HantoPlayerColor.BLUE, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void getRedPieceAtTest() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		HantoPiece testPiece = testGame.getPieceAt(new ConcreteHantoCoordinate(1, 0));

		assertEquals(HantoPlayerColor.RED, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void printableBoardTest() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals("(1,0)\tColor: RED\tType: Butterfly\n(0,0)\tColor: BLUE\tType: Butterfly\n",
				testGame.getPrintableBoard());
	}

	/***************************************************************************
	 * Begin Gamma tests
	 * *************************************************************************/
	@Test
	public void placeSparrowPiece() throws HantoException {
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
	}

	@Test(expected = HantoException.class)
	public void placeTwoButterflies() throws HantoException {
		// Blue's first move
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));

		// Red's first move
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));

		// Blue's second butterfly
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
	}

	@Test
	public void placeSBS() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
		result = testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void forceButterflyTest() throws HantoException {
		MoveResult result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(3, 0));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(4, 0));
		assertEquals(MoveResult.OK, result);

		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(5, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(6, 0));
	}

	@Test
	public void testRedStarts() throws HantoException {
		HantoGame game2 = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		MoveResult result = game2.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game2.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void yNot0() throws HantoException {
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 1));
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
	public void walkOneSpace() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));
		MoveResult result = testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
		assertNull(testGame.getPieceAt(new ConcreteHantoCoordinate(0, 0)));
		assertEquals(HantoPieceType.BUTTERFLY, testGame.getPieceAt(new ConcreteHantoCoordinate(1, 0)).getType());
	}

	@Test(expected = HantoException.class)
	public void moveNotYourPiece() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));
		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 1),
				new ConcreteHantoCoordinate(1, 0));

	}

	@Test(expected = HantoException.class)
	public void moveNonExistentPiece() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));
		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(5, 5),
				new ConcreteHantoCoordinate(5, 6));
	}

	@Test(expected = HantoException.class)
	public void breakChain() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-1, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(2, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(0, 1));

	}

	@Test(expected = HantoException.class)
	public void breakChainComplex() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-1, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(2, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-2, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(3, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-3, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(4, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-3, -1));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(5, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(-2, 1));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(4, 1));

		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(0, 1));
	}

	@Test
	public void cantSlideToDest() throws HantoException {
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
		testGame.setPlayerMoving(HantoPlayerColor.BLUE);
		testGame.setTurnNumber(10);

		try {
			testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(0, 0), new ConcreteHantoCoordinate(0,
					1));
		} catch (HantoException e) {
			assertEquals("Cannot slide to destination", e.getMessage());
		}
	}

	@Test
	public void cantMoveBeforeButterflyPlaced() throws HantoException {
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));

		boolean exceptionCaught = false;
		try {
			testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(0, 0), new ConcreteHantoCoordinate(0,
					1));
		} catch (HantoException e) {
			exceptionCaught = true;
			assertEquals("Player may not move pieces untill butterfly is placed", e.getMessage());
		}
		assertTrue(exceptionCaught);
	}

	@Test(expected = HantoException.class)
	public void cantMoveToOccupiedSpace() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));

		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(1, 0));
	}

	@Test(expected = HantoException.class)
	public void tryMoveMoreThanOne() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));

		testGame.makeMove(HantoPieceType.BUTTERFLY, new ConcreteHantoCoordinate(0, 0),
				new ConcreteHantoCoordinate(2, 0));
	}

	@Test(expected = HantoException.class)
	public void tryPlaceNextToEnemy() throws HantoException {
		testGame.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(2, 0));
	}

	/**
	 * Internal class for these test cases.
	 * 
	 * @version Sep 13, 2014
	 */
	class TestHantoCoordinate implements HantoCoordinate {
		private final int x, y;

		public TestHantoCoordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX() {
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY() {
			return y;
		}

	}

	@Test
	public void bluePlacesButterflyFirst() throws HantoException {
		final MoveResult mr = testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = testGame.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
	}

	@Test
	public void redPlacesSparrowFirst() throws HantoException {
		final MoveResult mr = testGame.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
	}

	@Test
	public void blueMovesSparrow() throws HantoException {
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		testGame.makeMove(SPARROW, null, makeCoordinate(0, -1));
		testGame.makeMove(SPARROW, null, makeCoordinate(0, 2));
		final MoveResult mr = testGame.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = testGame.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}

	@Test
	public void blueMovesSparrowUsingTestGame() throws HantoException {
		final PieceLocationPair[] board = new PieceLocationPair[] { plPair(BLUE, BUTTERFLY, 0, 0),
				plPair(RED, BUTTERFLY, 0, 1), plPair(BLUE, SPARROW, 0, -1), plPair(RED, SPARROW, 0, 2)

		};
		testGame.initializeBoard(board);
		testGame.setPlayerMoving(BLUE);
		testGame.setTurnNumber(3);
		final MoveResult mr = testGame.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = testGame.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}

	@Test
	public void moveButterfly() throws HantoException {
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		assertEquals(OK, testGame.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(1, 0)));
		final HantoPiece piece = testGame.getPieceAt(makeCoordinate(1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
		assertNull(testGame.getPieceAt(makeCoordinate(0, 0)));
	}

	@Test(expected = HantoException.class)
	public void moveToDisconnectConfiguration() throws HantoException {
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		testGame.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}

	@Test(expected = HantoException.class)
	public void attemptToMoveAPieceFromAnEmptyHex() throws HantoException {
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		testGame.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(0, -1));
	}

	@Test(expected = HantoException.class)
	public void attemptToMoveWrongPiece() throws HantoException {
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		testGame.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		testGame.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, 0));
	}

	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y) {
		return new TestHantoCoordinate(x, y);
	}

	/**
	 * Factory method to create a piece location pair.
	 * 
	 * @param player
	 *            the player color
	 * @param pieceType
	 *            the piece type
	 * @param x
	 *            starting location
	 * @param y
	 *            end location
	 * @return
	 */
	private PieceLocationPair plPair(HantoPlayerColor player, HantoPieceType pieceType, int x, int y) {
		return new PieceLocationPair(player, pieceType, new TestHantoCoordinate(x, y));
	}

}
