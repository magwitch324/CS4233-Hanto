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
import hanto.studentgrimshaw_mckenna.common.HantoGamePolicy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The alpha implementation of HantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoGame implements HantoGame {
	private BetaPlayer activePlayer;
	private BetaPlayer player1;
	private BetaPlayer player2;
	private double halfTurns;
	private boolean gameOver;
	private Map<BetaHantoCoordinate, BetaHantoPiece> board;
	private HantoGamePolicy policy;
	
	private int maxTurns = 6;

	/**
	 * Default constructor of AlphaHantoGame. Takes in the player color of who
	 * moves first
	 * 
	 * @param movesFirst
	 */
	public BetaHantoGame(HantoPlayerColor movesFirst) {
		board = new HashMap<BetaHantoCoordinate, BetaHantoPiece>();
		policy = new BetaHantoPolicy();
		player1 = new BetaPlayer(policy, movesFirst);
		if (movesFirst == HantoPlayerColor.BLUE) {
			player2 = new BetaPlayer(policy, HantoPlayerColor.RED);
		} else {
			player2 = new BetaPlayer(policy, HantoPlayerColor.BLUE);
		}
		setActivePlayer(player1);
		halfTurns = 1;
		gameOver = false;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {

		if(gameOver){
			throw new HantoException("Game is over");
		}
		BetaHantoPiece piece = new BetaHantoPiece(activePlayer.getColor(), pieceType);
		validateFirstTurn(to);

		// Validate that the piece is being placed not moved
		if (from == null) {
			if (!activePlayer.canPlacePiece(halfTurns, pieceType)) {
				throw new HantoException("Invalid piece");
			}

			// Validate the piece location
			piece.placePiece(board, new BetaHantoCoordinate(to));
			activePlayer.decrementPieceCount(pieceType);
		} else {
			throw new HantoException("No piece movement is allowed");
		}

		// Upon the player making a valid move switch the active player.
		changeActivePlayer();
		MoveResult result = checkGameStatus();
		halfTurns++;
		return result;
	}

	private MoveResult checkGameStatus() {
		MoveResult result;
		boolean blueSurrounded = false;
		boolean redSurrounded = false;
		
		//Iterate through the board and find the butterflies
		Iterator<Map.Entry<BetaHantoCoordinate, BetaHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BetaHantoCoordinate, BetaHantoPiece> pair = it.next();
			BetaHantoCoordinate coord = pair.getKey();
			BetaHantoPiece piece = pair.getValue();
			if(piece.getType() == HantoPieceType.BUTTERFLY){
				if(piece.getColor() == HantoPlayerColor.BLUE){
					blueSurrounded = isCoordinateSurrounded(coord);	
				}
				else{
					redSurrounded = isCoordinateSurrounded(coord);
				}
			}
		}
		
		
		//Check the results
		if((redSurrounded && blueSurrounded) || halfTurns/2 == maxTurns){
			result = MoveResult.DRAW;
			gameOver = true;
		}
		else if(redSurrounded){
			result = MoveResult.RED_WINS;
			gameOver = true;
		}
		else if(blueSurrounded){
			result = MoveResult.BLUE_WINS;
			gameOver = true;
		}
		else{
			result = MoveResult.OK;
		}
		
		return result;
	}

	private boolean isCoordinateSurrounded(BetaHantoCoordinate center) {
		BetaHantoCoordinate[] coords = center.getNeighborCoordinates();
		int i = 0;
		for (BetaHantoCoordinate coord : coords) {
			if (board.containsKey(coord)) {
				i++;
			}
		}
		return i == 6;
	}

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
		HantoPiece piece = board.get(coord);
		return piece;
	}

	@Override
	public String getPrintableBoard() {
		StringBuilder sb = new StringBuilder();

		Iterator<Map.Entry<BetaHantoCoordinate, BetaHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BetaHantoCoordinate, BetaHantoPiece> pair = it.next();
			BetaHantoPiece piece = pair.getValue();
			sb.append(pair.getKey());
			sb.append('\t');
			sb.append(piece);
			sb.append('\n');
		}

		return sb.toString();
	}

	/**
	 * @return the activePlayer
	 */
	public BetaPlayer getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer
	 * 
	 */
	public void setActivePlayer(BetaPlayer activePlayer) {
		this.activePlayer = activePlayer;
	}

}
