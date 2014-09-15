/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.beta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hanto.HantoGameFactory;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoCoordinate;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoGame;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for BetaHantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoGameTests {
	HantoGame game;

	@Before
	public void setUp() {
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO);
	}

	@Test
	public void gameNotNull() {
		assertNotNull(game);
	}

	@Test
	public void bluePlacesFirst() {
		assertEquals(HantoPlayerColor.BLUE, ((BetaHantoGame) game).getActivePlayer().getColor());
	}

	@Test
	public void canPlaceAt00() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void redPlacesSecond() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(HantoPlayerColor.RED, ((BetaHantoGame) game).getActivePlayer().getColor());
	}

	@Test(expected = HantoException.class)
	public void cantPlaceCrab() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new BetaHantoCoordinate(0, 0));

	}

	@Test(expected = HantoException.class)
	public void firstMoveNotAt00() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(5, 0));
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceOnTopOfOtherPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
	}

	@Test
	public void canPlacePieceNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceNotNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 1));
	}

	@Test
	public void getPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		HantoPiece testPiece = game.getPieceAt(new BetaHantoCoordinate(0, 0));

		assertEquals(HantoPlayerColor.BLUE, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void getRedPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		HantoPiece testPiece = game.getPieceAt(new BetaHantoCoordinate(1, 0));

		assertEquals(HantoPlayerColor.RED, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void printableBoardTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		assertEquals("(1,0)\tColor: RED\tType: Butterfly\n(0,0)\tColor: BLUE\tType: Butterfly\n",
				game.getPrintableBoard());
	}

	@Test(expected = HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, new BetaHantoCoordinate(0, 1), new BetaHantoCoordinate(0, 0));
	}

	/***************************************************************************
	 * Begin beta tests
	 * *************************************************************************/
	@Test
	public void placeSparrowPiece() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
	}

	@Test(expected = HantoException.class)
	public void placeTwoButterflies() throws HantoException {
		// Blue's first move
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));

		// Red's first move
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 1));

		// Blue's second butterfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
	}

	@Test
	public void placeSBS() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 1));
		assertEquals(MoveResult.OK, result);
	}

	@Test(expected = HantoException.class)
	public void forceButterflyTest() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(3, 0));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(4, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(5, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(6, 0));
	}

	@Test
	public void allPiecesPlaced() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(3, 0));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(4, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(5, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(6, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(7, 0));
		assertEquals(MoveResult.OK, result);

		// Turn5
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(8, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(9, 0));
		assertEquals(MoveResult.OK, result);

		// Turn6
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(10, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(11, 0));
		assertEquals(MoveResult.DRAW, result);
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceAfterGameOver() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(3, 0));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(4, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(5, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(6, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(7, 0));
		assertEquals(MoveResult.OK, result);

		// Turn5
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(8, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(9, 0));
		assertEquals(MoveResult.OK, result);

		// Turn6
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(10, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(11, 0));
		assertEquals(MoveResult.DRAW, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(11, 0));
	}

	@Test
	public void testRedStarts() throws HantoException {
		HantoGame game2 = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		MoveResult result = game2.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game2.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void blueWins() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 1));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, -1));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, -1));
		assertEquals(MoveResult.BLUE_WINS, result);

	}

	@Test
	public void redWins() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, -1));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, -1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(-1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(-1, 1));
		assertEquals(MoveResult.RED_WINS, result);

	}

	@Test
	public void drawTest() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new BetaHantoCoordinate(1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn 2
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, -1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, -1));
		assertEquals(MoveResult.OK, result);

		// Turn3
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, -1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(-1, 0));
		assertEquals(MoveResult.OK, result);

		// Turn4
		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(-1, 1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(2, 0));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(1, 1));
		assertEquals(MoveResult.OK, result);

		result = game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 1));
		assertEquals(MoveResult.DRAW, result);

	}

	@Test(expected = HantoException.class)
	public void yNot0() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, null, new BetaHantoCoordinate(0, 1));
	}

}
