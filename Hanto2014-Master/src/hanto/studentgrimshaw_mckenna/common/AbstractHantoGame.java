package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.beta.BetaHantoCoordinate;

public abstract class AbstractHantoGame implements HantoGame {

	private HantoBoard board;
	private HantoPlayer activePlayer;
	private HantoPlayer player1;
	private HantoPlayer player2;
	private double halfTurns;
	private boolean gameOver;
	private int maxTurns;

	public AbstractHantoGame(HantoPolicy policy, HantoBoard board) {
		this.board = board;
		maxTurns = policy.getMaxTurns();
		player1 = policy.constructPlayer1();
		player2 = policy.constructPlayer2();

		setActivePlayer(player1);
		halfTurns = 1;
		gameOver = false;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		checkGameOver();
		if (placingPiece(from)) {
			placePiece(pieceType, to);
		}
		// If the piece is being moved
		else {
			movePiece();
		}
		MoveResult result = resolveTurn();
		return result;
	}

	private MoveResult resolveTurn() {
		// Upon the player making a valid move switch the active player.
		changeActivePlayer();
		MoveResult result = checkGameStatus();
		halfTurns++;
		return result;
	}

	private void checkGameOver() throws HantoException {
		if (gameOver) {
			throw new HantoException("Game is over");
		}
	}

	protected void movePiece() throws HantoException {

	}

	private boolean placingPiece(HantoCoordinate from) {
		return from == null;
	}

	protected void placePiece(HantoPieceType pieceType, HantoCoordinate to) throws HantoException {
		validateFirstTurn(to);
		board.placePiece(activePlayer, halfTurns, pieceType, to);
	}

	/**
	 * Validates that the first piece is placed on 0,0
	 * 
	 * @param to
	 * @throws HantoException
	 */
	private void validateFirstTurn(HantoCoordinate to) throws HantoException {
		if (halfTurns == 1) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("Invalid first move!");
			}
		}
	}

	/**
	 * Switches the active player
	 */
	private void changeActivePlayer() {
		activePlayer = (activePlayer.equals(player2)) ? player1 : player2;
	}

	/**
	 * Checks for any game ending conditions and returns the result of the move
	 * 
	 * @return Result of the last move
	 */
	private MoveResult checkGameStatus() {
		MoveResult result = board.checkButterflies();

		// If the result is ok then check the turn count
		if (result == MoveResult.OK && halfTurns / 2 == maxTurns) {
			result = MoveResult.DRAW;
		}

		// if the game is over set the gameOver flag
		if (result != MoveResult.OK) {
			gameOver = true;
		}

		return result;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		BetaHantoCoordinate coord = new BetaHantoCoordinate(where);
		HantoPiece piece = board.getPieceAt(coord);
		return piece;
	}

	@Override
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}

	/**
	 * @return the activePlayer
	 */
	public HantoPlayer getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param player12
	 * 
	 */
	public void setActivePlayer(HantoPlayer player12) {
		this.activePlayer = player12;
	}

}
