package hanto.common;

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;
import hanto.studentgrimshaw_mckenna.epsilon.EpsilonHantoGame;

public class EpsilonHantoTestGame extends EpsilonHantoGame implements HantoTestGame {

	public EpsilonHantoTestGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		for (PieceLocationPair pair : initialPieces) {
			ConcreteHantoPiece piece = pieceFactory.makeHantoPiece(pair.player, pair.pieceType);
			ConcreteHantoCoordinate to = ConcreteHantoCoordinate.makeFrom(pair.location);
			board.placePiece(piece, to);

			if (player1.getColor() == pair.player) {
				player1.decrementPieceCount(pair.pieceType);
			} else {
				player2.decrementPieceCount(pair.pieceType);
			}
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;

	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		activePlayer = (player1.getColor() == player) ? player1 : player2;

	}

}
