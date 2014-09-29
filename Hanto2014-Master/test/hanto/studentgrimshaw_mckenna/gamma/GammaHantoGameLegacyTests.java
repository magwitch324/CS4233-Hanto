package hanto.studentgrimshaw_mckenna.gamma;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.factories.HantoGameFactory;

import org.junit.Before;
import org.junit.Test;

public class GammaHantoGameLegacyTests {

	HantoGame game;

	@Before
	public void setUp() {
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.GAMMA_HANTO);
	}

	@Test
	public void gameNotNull() {
		assertNotNull(game);
	}

	@Test
	public void bluePlacesFirst() {
		assertEquals(HantoPlayerColor.BLUE, ((GammaHantoGame) game).getActivePlayer().getColor());
	}

	@Test
	public void canPlaceAt00() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void redPlacesSecond() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(HantoPlayerColor.RED, ((GammaHantoGame) game).getActivePlayer().getColor());
	}

	@Test(expected = HantoException.class)
	public void cantPlaceCrab() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new ConcreteHantoCoordinate(0, 0));

	}

	@Test(expected = HantoException.class)
	public void firstMoveNotAt00() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(5, 0));
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceOnTopOfOtherPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
	}

	@Test
	public void canPlacePieceNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceNotNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 1));
	}

	@Test
	public void getPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		HantoPiece testPiece = game.getPieceAt(new ConcreteHantoCoordinate(0, 0));

		assertEquals(HantoPlayerColor.BLUE, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void getRedPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		HantoPiece testPiece = game.getPieceAt(new ConcreteHantoCoordinate(1, 0));

		assertEquals(HantoPlayerColor.RED, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void printableBoardTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals("(1,0)\tColor: RED\tType: Butterfly\n(0,0)\tColor: BLUE\tType: Butterfly\n",
				game.getPrintableBoard());
	}

	/***************************************************************************
	 * Begin Gamma tests
	 * *************************************************************************/
	@Test
	public void placeSparrowPiece() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
	}

	@Test(expected = HantoException.class)
	public void placeTwoButterflies() throws HantoException {
		// Blue's first move
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 0));

		// Red's first move
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(0, 1));

		// Blue's second butterfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
	}

	@Test
	public void placeSBS() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new ConcreteHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void forceButterflyTest() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(3, 0));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(4, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(5, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(6, 0));
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
		game.makeMove(HantoPieceType.SPARROW, null, new ConcreteHantoCoordinate(0, 1));
	}

}
