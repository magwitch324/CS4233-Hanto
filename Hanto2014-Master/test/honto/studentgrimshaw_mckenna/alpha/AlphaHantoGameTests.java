/**
 * 
 */
package honto.studentgrimshaw_mckenna.alpha;

import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.*;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoCoordinate;
import hanto.studentgrimshaw_mckenna.alpha.AlphaHantoGame;

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

}
