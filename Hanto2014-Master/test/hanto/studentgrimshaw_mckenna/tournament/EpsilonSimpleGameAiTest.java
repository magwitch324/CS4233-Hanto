package hanto.studentgrimshaw_mckenna.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.factories.HantoPieceFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

public class EpsilonSimpleGameAiTest {
	HantoPieceFactory factory = new HantoPieceFactory(HantoGameID.EPSILON_HANTO);

	@Test
	public void testNoMovesAvailable() throws HantoException {
		EpsilonSimpleGameAi ai = (EpsilonSimpleGameAi) GameAiFactory.getInstance().makeGameAi(
				HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoBoard board = ai.getBoard();
		HantoPlayer player = ai.getActivePlayer();
		placePiece(board, player, HantoPieceType.BUTTERFLY, coord(0, -1));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 0));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 1));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 2));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 3));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 4));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 5));
		placePiece(board, player, HantoPieceType.HORSE, coord(0, 6));
		placePiece(board, player, HantoPieceType.HORSE, coord(0, 7));
		placePiece(board, player, HantoPieceType.HORSE, coord(0, 8));
		placePiece(board, player, HantoPieceType.HORSE, coord(0, 9));
		placePiece(board, player, HantoPieceType.SPARROW, coord(0, 10));
		placePiece(board, player, HantoPieceType.SPARROW, coord(0, 11));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, -2));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, 12));
		HantoMoveRecord record = ai.MakeAiMove();
		assertNull(record.getFrom());
		assertNull(record.getTo());
		assertNull(record.getPiece());
	}

	@Test
	public void testNoPlacement() throws HantoException {
		EpsilonSimpleGameAi ai = (EpsilonSimpleGameAi) GameAiFactory.getInstance().makeGameAi(
				HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoBoard board = ai.getBoard();
		HantoPlayer player = ai.getActivePlayer();
		placePiece(board, player, HantoPieceType.BUTTERFLY, coord(0, 0));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, 1));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(1, -1));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(-1, 0));
		ai.setTurn(2);
		HantoMoveRecord record = ai.MakeAiMove();
		assertNull(record.getFrom());
		assertNull(record.getTo());
		assertNull(record.getPiece());
	}

	@Test
	public void testNoPlacementThenMove() throws HantoException {
		EpsilonSimpleGameAi ai = (EpsilonSimpleGameAi) GameAiFactory.getInstance().makeGameAi(
				HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoBoard board = ai.getBoard();
		HantoPlayer player = ai.getActivePlayer();
		placePiece(board, player, HantoPieceType.BUTTERFLY, coord(0, 0));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(-1, 1));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(1, -1));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, 1));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(1, 0));
		ai.setTurn(2);
		HantoMoveRecord record = ai.MakeAiMove();
		assertNotNull(record.getFrom());
		assertNotNull(record.getTo());
		assertNotNull(record.getPiece());
	}

	@Test
	public void testForcedSuicide() throws HantoException {
		EpsilonSimpleGameAi ai = (EpsilonSimpleGameAi) GameAiFactory.getInstance().makeGameAi(
				HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		HantoBoard board = ai.getBoard();
		HantoPlayer player = ai.getActivePlayer();
		placePiece(board, player, HantoPieceType.BUTTERFLY, coord(0, 0));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, 1));
		placePiece(board, player, HantoPieceType.CRAB, coord(1, 0));
		placePiece(board, player, HantoPieceType.CRAB, coord(1, -1));
		placePiece(board, player, HantoPieceType.CRAB, coord(0, -1));
		placePiece(board, player, HantoPieceType.CRAB, coord(-1, 0));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, 2));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(2, 0));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(2, -2));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(0, -2));
		board.placePiece(factory.makeHantoPiece(HantoPlayerColor.RED, HantoPieceType.CRAB), coord(-2, 0));
		ai.setTurn(2);
		HantoMoveRecord record = ai.MakeAiMove();
		assertNull(record.getFrom());
		assertEquals(coord(-1, 1), record.getTo());
		assertNotNull(record.getPiece());
	}

	private ConcreteHantoCoordinate coord(int x, int y) {
		return new ConcreteHantoCoordinate(x, y);
	}

	private void placePiece(HantoBoard board, HantoPlayer player, HantoPieceType type, ConcreteHantoCoordinate coord) {

		ConcreteHantoPiece piece = factory.makeHantoPiece(player.getColor(), type);
		board.placePiece(piece, coord);
		player.decrementPieceCount(type);
	}

}
