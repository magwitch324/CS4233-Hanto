/**
 * 
 */
package honto.studentgrimshaw_mckenna.alpha;

import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.*;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoGame;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoPiece;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tag
 *
 */
public class AlphaHantoGameTests {
	HantoGame game;

	@Before
	public void setUp() {
		game = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
	}

	@Test
	public void gameNotNull() {
		assertNotNull(game);
	}

	@Test
	public void bluePlacesFirst() {
		assertEquals(HantoPlayerColor.BLUE,
				((AlphaHantoGame) game).getActivePlayer());
	}

	@Test
	public void canPlaceAt00() throws HantoException {
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null,
				new AlphaHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, result);
	}

	@Test
	public void redPlacesSecond() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(
				0, 0));
		assertEquals(HantoPlayerColor.RED,
				((AlphaHantoGame) game).getActivePlayer());
	}

	@Test(expected = HantoException.class)
	public void cantPlaceCrab() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, new AlphaHantoCoordinate(0, 0)); 

	}

	@Test(expected = HantoException.class)
	public void firstMoveNotAt00() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(5,0));
	}
	

	@Test(expected = HantoException.class)
	public void cantPlacePieceOnTopOfOtherPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0,0));
		assertEquals(MoveResult.OK, result);
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0,0));
	}
	
	@Test
	public void canPlacePieceNextToExistingPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0,0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0,1));
		assertEquals(MoveResult.DRAW, result);
	}
	
	@Test(expected = HantoException.class)
	public void cantPlacePieceNotNextToExistingPiece() throws HantoException{
		MoveResult result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0,0));
		assertEquals(MoveResult.OK, result);
		result = game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(1,1));
	}
	
	@Test
	public void getPieceAtTest() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new AlphaHantoCoordinate(0, 0));
		HantoPiece testPiece = game.getPieceAt(new AlphaHantoCoordinate(0, 0));
		
		assertEquals(HantoPlayerColor.BLUE, testPiece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, testPiece.getType());
	}

}
