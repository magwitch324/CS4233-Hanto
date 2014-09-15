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

import java.util.Map;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Alpha implementation of HantoPiece
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoPiece implements HantoPiece {
	HantoPlayerColor color;
	HantoPieceType type;

	/**
	 * Default constructor for AlphaHantoPiece.
	 * 
	 * @param color
	 *            Color of the piece
	 * @param type
	 *            Type of piece
	 */
	public BetaHantoPiece(HantoPlayerColor color, HantoPieceType type) {
		this.color = color;
		this.type = type;
	}

	/**
	 * Places the specified piece on the board
	 * 
	 * @param board
	 *            The HantoGameBoard
	 * @param to
	 *            The position in which to place the piece
	 * @throws HantoException
	 */
	public void placePiece(Map<BetaHantoCoordinate, BetaHantoPiece> board, BetaHantoCoordinate to)
			throws HantoException {

		if (!board.containsKey(to)) {
			boolean hasNeighborPiece = false;
			BetaHantoCoordinate[] neighbors = to.getNeighborCoordinates();
			for (int i = 0; i < 6; i++) {
				BetaHantoCoordinate neighbor = neighbors[i];
				if (board.containsKey(neighbor)) {
					hasNeighborPiece = true;
				}
			}
			if (!hasNeighborPiece && board.size() != 0) {
				throw new HantoException("No neighboring pieces!");
			}

			board.put(to, this);
		} else {
			throw new HantoException("Destination already occupied");
		}
	}

	@Override
	public HantoPlayerColor getColor() {

		return color;
	}

	@Override
	public HantoPieceType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public String toString() {
		return "Color: " + color + "\tType: " + type;
	}

}
