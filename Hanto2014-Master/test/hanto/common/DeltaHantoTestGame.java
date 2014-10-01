package hanto.common;

import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;
import hanto.studentgrimshaw_mckenna.delta.DeltaHantoGame;

public class DeltaHantoTestGame extends DeltaHantoGame implements HantoTestGame  {

	public DeltaHantoTestGame(HantoPolicy policy, HantoBoard board)  {
		super(policy, board);
		// TODO Auto-generated constructor stub
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
