/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

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
public class AlphaHantoGame implements HantoGame {
	private HantoPlayerColor activePlayer;
	private boolean isFirstTurn;
	private Map<AlphaHantoCoordinate, AlphaHantoPiece> board;

	/**
	 * Default constructor of AlphaHantoGame. Takes in the player color of who
	 * moves first
	 * 
	 * @param movesFirst
	 */
	public AlphaHantoGame(HantoPlayerColor movesFirst) {
		board = new HashMap<AlphaHantoCoordinate, AlphaHantoPiece>();
		this.setActivePlayer(movesFirst);
		isFirstTurn = true;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		AlphaHantoPiece piece = new AlphaHantoPiece(activePlayer, pieceType);
		// Ensure the piece type is correct for this version of Hanto
		validatePieceType(pieceType);
		// Validate the piece location
		validatePieceLocation(new AlphaHantoCoordinate(to), piece);

		// Upon the player making a valid move switch the active player.
		changeActivePlayer();
		MoveResult result = (isFirstTurn) ? MoveResult.OK : MoveResult.DRAW;
		isFirstTurn = false;
		return result;
	}

	/**
	 * 
	 * @param to
	 * @throws HantoException
	 */
	private void validatePieceLocation(AlphaHantoCoordinate to, AlphaHantoPiece piece) throws HantoException {

		if (isFirstTurn) {
			if (to.getX() != 0 || to.getY() != 0) {
				throw new HantoException("Invalid first move!");
			} else {
				board.put(to, piece);
			}
		} else {
			if (!board.containsKey(to)) {
				boolean hasNeighborPiece = false;
				AlphaHantoCoordinate[] neighbors = to.getNeighborCoordinates();
				for (int i = 0; i < 6; i++) {
					AlphaHantoCoordinate neighbor = neighbors[i];
					if (board.containsKey(neighbor)) {
						hasNeighborPiece = true;
					}
				}

				if (!hasNeighborPiece) {
					throw new HantoException("No neighboring pieces!");
				}

				board.put(to, piece);
			} else {
				throw new HantoException("Destination already occupied");
			}
		}
	}

	/**
	 * 
	 * @param pieceType
	 * @throws HantoException
	 */
	private void validatePieceType(HantoPieceType pieceType) throws HantoException {
		if (pieceType != HantoPieceType.BUTTERFLY) {
			throw new HantoException("Invalid Piece Type");
		}
	}

	/**
	 * Switches the active player
	 */
	private void changeActivePlayer() {
		activePlayer = (activePlayer == HantoPlayerColor.BLUE) ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		AlphaHantoCoordinate coord = new AlphaHantoCoordinate(where);
		HantoPiece piece = board.get(coord);
		return piece;
	}

	@Override
	public String getPrintableBoard() {
		StringBuilder sb = new StringBuilder();

		Iterator<Map.Entry<AlphaHantoCoordinate, AlphaHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<AlphaHantoCoordinate, AlphaHantoPiece> pair = it.next();
			AlphaHantoPiece piece = pair.getValue();
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
	public HantoPlayerColor getActivePlayer() {
		return activePlayer;
	}

	/**
	 * @param activePlayer
	 * 
	 */
	public void setActivePlayer(HantoPlayerColor activePlayer) {
		this.activePlayer = activePlayer;
	}

}
