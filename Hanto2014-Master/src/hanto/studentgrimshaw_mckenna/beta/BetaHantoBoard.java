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
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPlayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Beta implementation of the HantoBoard
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoBoard implements HantoBoard {
	private Map<BetaHantoCoordinate, BetaHantoPiece> board;

	/**
	 * Default constructor the BetaHantoBoard. Initializes the board
	 */
	public BetaHantoBoard() {
		board = new HashMap<BetaHantoCoordinate, BetaHantoPiece>();
	}

	@Override
	public void placePiece(HantoPlayer player, double halfTurns, HantoPieceType pieceType, HantoCoordinate to)
			throws HantoException {
		BetaHantoPiece piece = new BetaHantoPiece(player.getColor(), pieceType);

		// If the player does not has the piece type in their hand throw an
		// exception
		if (!player.canPlacePiece(halfTurns, pieceType)) {
			throw new HantoException("Invalid piece");
		}

		// Validate the piece location
		piece.placePiece(board, new BetaHantoCoordinate(to));
		player.decrementPieceCount(pieceType);
	}

	@Override
	public boolean isCoordinateSurrounded(BetaHantoCoordinate center) {
		BetaHantoCoordinate[] coords = center.getNeighborCoordinates();
		int i = 0;
		for (BetaHantoCoordinate coord : coords) {
			if (board.containsKey(coord)) {
				i++;
			}
		}
		return i == 6;
	}

	@Override
	public MoveResult checkButterflies() {
		MoveResult result;
		boolean blueSurrounded = false;
		boolean redSurrounded = false;

		// Iterate through the board and find the butterflies
		Iterator<Map.Entry<BetaHantoCoordinate, BetaHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<BetaHantoCoordinate, BetaHantoPiece> pair = it.next();
			BetaHantoCoordinate coord = pair.getKey();
			BetaHantoPiece piece = pair.getValue();

			if (piece.getType() == HantoPieceType.BUTTERFLY) {
				if (piece.getColor() == HantoPlayerColor.BLUE) {
					blueSurrounded = isCoordinateSurrounded(coord);
				} else {
					redSurrounded = isCoordinateSurrounded(coord);
				}
			}
		}

		// Check the result
		// If both butterflies are surrounded or the game has hit max turns
		if ((redSurrounded && blueSurrounded)) {
			result = MoveResult.DRAW;
		} else if (redSurrounded) {
			result = MoveResult.BLUE_WINS;
		} else if (blueSurrounded) {
			result = MoveResult.RED_WINS;
		} else {
			result = MoveResult.OK;
		}

		return result;
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate coord) {
		return board.get(new BetaHantoCoordinate(coord));
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
}
