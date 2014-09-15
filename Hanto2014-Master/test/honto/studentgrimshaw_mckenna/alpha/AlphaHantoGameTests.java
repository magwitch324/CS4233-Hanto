/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package honto.studentgrimshaw_mckenna.alpha;

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
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoGame;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for AlphaHantoGame
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class AlphaHantoGameTests {
	HantoGame game;

	@Before
	public void setUp() {
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.ALPHA_HANTO);
	}

	@Test
	public void gameNotNull() {
		assertNotNull(game);
	}

	@Test
	public void bluePlacesFirst() {
		assertEquals(HantoPlayerColor.BLUE, ((AlphaHantoGame) game).getActivePlayer());
	}

	@Test
	public void canPlaceAt00() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void redPlacesSecond() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(HantoPlayerColor.RED, ((AlphaHantoGame) game).getActivePlayer());
	}

	@Test(expected = HantoException.class)
	public void cantPlaceCrab() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new AlphaHantoCoordinate(0, 0));

	}

	@Test(expected = HantoException.class)
	public void firstMoveNotAt00() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(5, 0));
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceOnTopOfOtherPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
	}

	@Test
	public void canPlacePieceNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 1));
		assertEquals(MoveResult.DRAW, result);
	}

	@Test(expected = HantoException.class)
	public void cantPlacePieceNotNextToExistingPiece() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(1, 1));
	}

	@Test
	public void getPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		HantoPiece testPiece = game.getPieceAt(new AlphaHantoCoordinate(0, 0));

		assertEquals(HantoPlayerColor.BLUE, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void getRedPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(1, 0));
		HantoPiece testPiece = game.getPieceAt(new AlphaHantoCoordinate(1, 0));

		assertEquals(HantoPlayerColor.RED, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

	@Test
	public void printableBoardTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(1, 0));
		assertEquals("(1,0)\tColor: RED\tType: Butterfly\n(0,0)\tColor: BLUE\tType: Butterfly\n",
				game.getPrintableBoard());
	}
	
	@Test(expected = HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, new AlphaHantoCoordinate(0, 1), new AlphaHantoCoordinate(0, 0));
	}
	
	@Test(expected = HantoException.class)
	public void attemptToPlaceAfterGameIsOver() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 1));
		assertEquals(MoveResult.DRAW, result);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(1, 0));
		
	}

}
