package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoPlayer;

public class AbstractHantoGame implements HantoGame {

	private HantoBoard board;
	private HantoPlayer activePlayer;
	private HantoPlayer player1;
	private HantoPlayer player2;
	private double halfTurns;
	private boolean gameOver;
	private HantoPolicy policy;
	
	public public AbstractHantoGame(HantoPlayerColor movesFirst) {
		HantoPlayerColor movesSecond = movesFirst == HantoPlayerColor.BLUE ? HantoPlayerColor.RED
				: HantoPlayerColor.BLUE;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
