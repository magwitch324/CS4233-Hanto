package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;

public abstract class AbstractHantoGame implements HantoGame {

	protected HantoPlayer player1;
	protected HantoPlayer player2;
	protected int turnNumber;	
	private boolean gameOver;
	private int maxTurns;
	protected PieceFactory pieceFactory;
	protected HantoPlayer activePlayer;
	protected HantoBoard board;

	public AbstractHantoGame(HantoPolicy policy, HantoBoard board) {

		// create game environment
		this.board = board;
		pieceFactory = new PieceFactory(policy.getId());
		maxTurns = policy.getMaxTurns();

		// Create players
		player1 = policy.constructPlayer1();
		player2 = policy.constructPlayer2();

		// set game conditions
		setActivePlayer(player1);
		turnNumber = 1;
		gameOver = false;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo)
			throws HantoException {
		checkGameOver();
		ConcreteHantoCoordinate to = ConcreteHantoCoordinate.makeFrom(hTo);
		ConcreteHantoCoordinate from = ConcreteHantoCoordinate.makeFrom(hFrom);

		if (placingPiece(from)) {
			placePiece(pieceType, to);
		} else {
			movePiece();
		}
		MoveResult result = resolveTurn();
		return result;
	}

	private void checkGameOver() throws HantoException {
		if (gameOver) {
			throw new HantoException("Game is over");
		}
	}

	private boolean placingPiece(ConcreteHantoCoordinate from) {
		return from == null;
	}

	protected void placePiece(HantoPieceType pieceType, ConcreteHantoCoordinate to) throws HantoException {
		validateFirstTurn(to);

		// construct the piece
		ConcreteHantoPiece piece = pieceFactory.makeHantoPiece(activePlayer.getColor(), pieceType);

		// If the player does not have the piece type in their hand throw an
		// exception
		if (!activePlayer.canPlacePiece(turnNumber, pieceType)) {
			throw new HantoException("Invalid piece");
		}

		board.checkCanPlacePiece(piece, to);
		board.placePiece(piece, to);
		activePlayer.decrementPieceCount(pieceType);
	}

	protected void movePiece() throws HantoException {

	}

	private MoveResult resolveTurn() {
		// Upon the player making a valid move switch the active player.
		changeActivePlayer();
		MoveResult result = checkGameStatus();
		return result;
	}

	/**
	 * Validates that the first piece is placed on 0,0
	 * 
	 * @param to
	 * @throws HantoException
	 */
	private void validateFirstTurn(ConcreteHantoCoordinate to) throws HantoException {
		if (turnNumber == 1 && activePlayer == player1) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("Invalid first move!");
			}
		}
	}

	/**
	 * Switches the active player
	 */
	private void changeActivePlayer() {
		if (activePlayer.equals(player2)) {
			activePlayer = player1;
			turnNumber++;
		} else {
			activePlayer = player2;
		}
	}

	/**
	 * Checks for any game ending conditions and returns the result of the move
	 * 
	 * @return Result of the last move
	 */
	private MoveResult checkGameStatus() {
		MoveResult result = board.checkButterflies();

		// If the result is ok then check the turn count
		if (result == MoveResult.OK && turnNumber > maxTurns) {
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
		ConcreteHantoCoordinate coord = ConcreteHantoCoordinate.makeFrom(where);
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
