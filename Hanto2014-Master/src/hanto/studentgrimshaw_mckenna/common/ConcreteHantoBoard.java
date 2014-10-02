/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.exceptions.HantoPlacementException;
import hanto.studentgrimshaw_mckenna.common.interfaces.PlacementNeighborValidator;

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
public class ConcreteHantoBoard implements HantoBoard {
	private Map<ConcreteHantoCoordinate, ConcreteHantoPiece> board;
	private PlacementNeighborValidator validator;

	/**
	 * Default constructor the BetaHantoBoard. Initializes the board
	 */
	public ConcreteHantoBoard() {
		board = new HashMap<ConcreteHantoCoordinate, ConcreteHantoPiece>();
	}

	@Override
	public void placePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to) {
		board.put(to, piece);
	}

	@Override
	public boolean isCoordinateSurrounded(ConcreteHantoCoordinate center) {
		ConcreteHantoCoordinate[] coords = center.getNeighborCoordinates();
		int i = 0;
		for (ConcreteHantoCoordinate coord : coords) {
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
		Iterator<Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece> pair = it.next();
			ConcreteHantoCoordinate coord = pair.getKey();
			ConcreteHantoPiece piece = pair.getValue();

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
	public HantoPiece getPieceAt(ConcreteHantoCoordinate coord) {
		return board.get(coord);
	}

	@Override
	public String getPrintableBoard() {
		StringBuilder sb = new StringBuilder();

		Iterator<Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece>> it = board.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<ConcreteHantoCoordinate, ConcreteHantoPiece> pair = it.next();
			ConcreteHantoPiece piece = pair.getValue();
			sb.append(pair.getKey());
			sb.append('\t');
			sb.append(piece);
			sb.append('\n');
		}

		return sb.toString();
	}

	@Override
	public void checkCanPlacePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to) throws HantoException {
		if (!board.containsKey(to)) {
			boolean hasValidNeighborPiece = false;
			boolean hasInvalidNeighborPiece = false;
			ConcreteHantoCoordinate[] neighbors = to.getNeighborCoordinates();
			for (int i = 0; i < 6; i++) {
				ConcreteHantoCoordinate neighbor = neighbors[i];
				if (board.containsKey(neighbor)) {
					if (validator.isValidNeighborPiece(piece, board.get(neighbor))) {
						hasValidNeighborPiece = true;
					} else {
						hasInvalidNeighborPiece = true;
					}
				}
			}

			if (hasInvalidNeighborPiece) {
				if (board.size() != 1) {

					throw new HantoPlacementException("Cannot place next to enemy piece");
				}

			} else if (!hasValidNeighborPiece && board.size() != 0) {
				throw new HantoException("No neighboring allied pieces!");
			}

		} else {
			throw new HantoException("Destination already occupied");
		}
	}

	@Override
	public ConcreteHantoPiece checkMovePiece(HantoPlayerColor color, HantoPieceType pieceType,
			ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) throws HantoException {
		ConcreteHantoPiece piece = board.get(from);

		if (piece == null) {
			throw new HantoException("Piece does not exist at this location");
		}
		if (piece.getColor() != color) {
			throw new HantoException("Cannot move other player's piece.");
		}
		if (!piece.getType().equals(pieceType)) {
			throw new HantoException("Incorrect piece type for the specified location");
		}
		piece.validateMove(board, from, to);
		return board.get(from);
	}

	@Override
	public void movePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to) {
		board.remove(from);
		board.put(to, piece);
	}

	public void setPlacementNeighborValidator(PlacementNeighborValidator validator) {
		this.validator = validator;
	}
}
