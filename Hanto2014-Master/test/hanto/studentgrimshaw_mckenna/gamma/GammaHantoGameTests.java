/**
 * 
 */
package hanto.studentgrimshaw_mckenna.gamma;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tag
 *
 */
public class GammaHantoGameTests {

	HantoTestGame testGame;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.GAMMA_HANTO);
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

	@Test(expected = HantoException.class)
	public void drawAfter20Turns() throws HantoException {
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
		assertEquals(MoveResult.DRAW, result);

		testGame.makeMove(HantoPieceType.SPARROW, new ConcreteHantoCoordinate(-1, 1),
				new ConcreteHantoCoordinate(-2, 2));

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

		testGame.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 1));
	}

}
