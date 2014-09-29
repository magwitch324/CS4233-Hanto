package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.factories.PieceFactory;

public abstract class AbstractHantoGame implements HantoGame {

	//Game state variables
	private boolean gameOver;
	private int maxTurns;
	protected int turnNumber;
	
	//Game objects
	protected PieceFactory pieceFactory;
	protected HantoBoard board;
	
	//Player variables
	protected HantoPlayer activePlayer;
	protected HantoPlayer player1;
	protected HantoPlayer player2;
	

	protected AbstractHantoGame(HantoPolicy policy, HantoBoard board) {

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

		MoveResult result;

		checkGameOver();
		result = isResigning(pieceType, hFrom, hTo);
		if (result == null) {

			ConcreteHantoCoordinate to = ConcreteHantoCoordinate.makeFrom(hTo);
			ConcreteHantoCoordinate from = ConcreteHantoCoordinate.makeFrom(hFrom);

			if (placingPiece(from)) {
				placePiece(pieceType, to);
			} else {
				movePiece(pieceType, from , to);
			}
			result = resolveTurn();
		}
		return result;
	}

	private void checkGameOver() throws HantoException {
		if (gameOver) {
			throw new HantoException("Game is over");
		}
	}

	/**
	 * This method returns the move result when a player resigns. When a player
	 * resigns, that player's opponent wins the game.
	 * 
	 * @param pieceType
	 *            Null if resigning.
	 * @param hFrom
	 *            Null if resigning.
	 * @param hTo
	 *            Null if resigning.
	 * @return The move result.
	 */
	protected MoveResult isResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo) {
		MoveResult result = null;

		if (pieceType == null && hFrom == null && hTo == null) {
			if (activePlayer.getColor() == HantoPlayerColor.BLUE) {
				result = MoveResult.RED_WINS;
			} else {
				result = MoveResult.BLUE_WINS;
			}
		}
		return result;
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

	protected void movePiece(HantoPieceType pieceType, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {
		if (!activePlayer.canMovePiece()) {
			throw new HantoException("Player may not move pieces untill butterfly is placed");
		}
		ConcreteHantoPiece piece = board.checkMovePiece(activePlayer.getColor(), pieceType, from, to);
		board.movePiece(piece, from, to);
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
		activePlayer = player12;
	}

}
