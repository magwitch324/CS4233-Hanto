/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.abstracts;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.factories.HantoPieceFactory;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

/**
 * Abstract class for HantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public abstract class AbstractHantoGame implements HantoGame {

	// Game state variables
	protected boolean gameOver;
	private int maxTurns;
	protected int turnNumber;

	// Game objects
	protected HantoPieceFactory pieceFactory;
	protected HantoBoard board;

	// Player variables
	protected HantoPlayer activePlayer;
	protected HantoPlayer player1;
	protected HantoPlayer player2;

	/**
	 * Default constructor for AbstractHantoGame
	 * 
	 * @param policy
	 *            Policy containing the rules of the game
	 * @param board
	 *            Board on which the game is played
	 */
	protected AbstractHantoGame(HantoPolicy policy, HantoBoard board) {

		// create game environment
		this.board = board;
		pieceFactory = new HantoPieceFactory(policy.getId());
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
		result = checkResigning(pieceType, hFrom, hTo);

		if (result == null) {
			// convert the coordinates
			ConcreteHantoCoordinate to = ConcreteHantoCoordinate.makeFrom(hTo);
			ConcreteHantoCoordinate from = ConcreteHantoCoordinate.makeFrom(hFrom);

			if (isPlacingPiece(from)) {
				placePiece(pieceType, to);
			} else {
				movePiece(pieceType, from, to);
			}
			result = resolveTurn();
		}
		return result;
	}

	/**
	 * Throws an error is a move is made after the game is over
	 * 
	 * @throws HantoException
	 */
	private void checkGameOver() throws HantoException {
		if (gameOver) {
			throw new HantoException("Game is over");
		}
	}

	/**
	 * This method returns a move result when a player resigns. When a player
	 * resigns, that player's opponent wins the game.
	 * 
	 * @param pieceType
	 *            Null if resigning.
	 * @param hFrom
	 *            Null if resigning.
	 * @param hTo
	 *            Null if resigning.
	 * @return The move result.
	 * @throws HantoPrematureResignationException
	 */
	protected MoveResult checkResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo)
			throws HantoPrematureResignationException {
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

	/**
	 * Determines if the player is placing the piece
	 * 
	 * @param from
	 *            Coordinate the piece is coming from
	 * @return True if the piece is being placed
	 */
	private boolean isPlacingPiece(ConcreteHantoCoordinate from) {
		return from == null;
	}

	/**
	 * Places the piece on the board after checking that the placement is valid
	 * and the player can place it.
	 * 
	 * @param pieceType
	 *            Type of piece being p,aced
	 * @param to
	 *            Coordinate it is being placed on
	 * @throws HantoException
	 */
	protected void placePiece(HantoPieceType pieceType, ConcreteHantoCoordinate to) throws HantoException {
		validateFirstTurn(to);

		// construct the piece
		ConcreteHantoPiece piece = pieceFactory.makeHantoPiece(activePlayer.getColor(), pieceType);

		// If the player does not have the piece type in their hand throw an
		// exception
		if (!activePlayer.canPlacePiece(turnNumber, pieceType)) {
			throw new HantoException("Player cannot place piece");
		}

		board.checkCanPlacePiece(piece, to);
		board.placePiece(piece, to);
		activePlayer.decrementPieceCount(pieceType);
	}

	/**
	 * Moves the piece after checking that the move is valid and that the player
	 * can make the move
	 * 
	 * @param pieceType
	 *            Type of piece to move
	 * @param from
	 *            Where the piece is now
	 * @param to
	 *            Desired destination
	 * @throws HantoException
	 */
	protected void movePiece(HantoPieceType pieceType, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to)
			throws HantoException {
		if (!activePlayer.canMovePieces()) {
			throw new HantoException("Player may not move pieces untill butterfly is placed");
		}
		board.checkMovePiece(activePlayer.getColor(), pieceType, from, to);
		board.movePiece(from, to);
	}

	/**
	 * Resolves the turn and determines if the game has ended
	 * 
	 * @return Result of the last move
	 */
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
		if (turnNumber == 1 && activePlayer.equals(player1)) {
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
