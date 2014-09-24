/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.beta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;

/**
 * The alpha implementation of HantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoGame implements HantoGame {
	
	



	

	private int maxTurns = 6;

	/**
	 * Default constructor of AlphaHantoGame. Takes in the player color of who
	 * moves first
	 * 
	 * @param movesFirst
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		HantoPlayerColor movesSecond = movesFirst == HantoPlayerColor.BLUE ? HantoPlayerColor.RED
				: HantoPlayerColor.BLUE;

		board = new BetaHantoBoard();
		policy = new BetaHantoPolicy();

		player1 = new BetaHantoPlayer(policy, movesFirst);
		player2 = new BetaHantoPlayer(policy, movesSecond);
		setActivePlayer(player1);

		halfTurns = 1;
		gameOver = false;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		// Check that the game isnt over
		if (gameOver) {
			throw new HantoException("Game is over");
		}

		validateFirstTurn(to);

		// If the piece is coming from the players hand
		if (from == null) {
			board.placePiece(activePlayer, halfTurns, pieceType, to);
		}
		// If the piece is being moved
		else {
			throw new HantoException("No piece movement is allowed");
		}

		// Upon the player making a valid move switch the active player.
		changeActivePlayer();
		MoveResult result = checkGameStatus();
		halfTurns++;
		return result;
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
	public BetaHantoPlayer getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer
	 * 
	 */
	public void setActivePlayer(BetaHantoPlayer activePlayer) {
		this.activePlayer = activePlayer;
	}

}
