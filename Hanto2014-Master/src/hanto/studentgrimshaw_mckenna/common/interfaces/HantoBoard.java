/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.interfaces;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

/**
 * Interface for the board used in hanto game. Holds and handles information
 * related to the pieces on the board
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface HantoBoard {

	/**
	 * Sets the placement neighbor validator
	 * 
	 * @param validator
	 *            The neighbor validator
	 */
	void setPlacementNeighborValidator(PlacementNeighborValidator validator);

	/**
	 * Checks if the piece can be placed in the specified location
	 * 
	 * @param piece
	 *            Piece to place
	 * @param to
	 *            Location to check
	 * @throws HantoException
	 *             If the placement is invalid
	 */
	void checkCanPlacePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to) throws HantoException;

	/**
	 * Places the piece in the specified location
	 * 
	 * @param piece
	 *            Piece to place
	 * @param to
	 *            Location in which to place it
	 */
	void placePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to);

	/**
	 * Checks if the piece can be move from the sepcified location to a new
	 * specified location
	 * 
	 * @param color
	 *            Color of the player moving the piece
	 * @param pieceType
	 *            Type of piece to move
	 * @param from
	 *            Location of the piece
	 * @param to
	 *            Desired destination
	 * @throws HantoException
	 */
	void checkMovePiece(HantoPlayerColor color, HantoPieceType pieceType, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException;

	/**
	 * Moves the piece in the specified location to the specified destination
	 * 
	 * @param from
	 *            Location of the piece
	 * @param to
	 *            Destination
	 */
	void movePiece(ConcreteHantoCoordinate from, ConcreteHantoCoordinate to);

	/**
	 * Checks whether either butterfly is surrounded and returns the game result
	 * 
	 * @return The game result from the last move
	 */
	MoveResult checkButterflies();

	/**
	 * Gets the Hanto Piece at the specified coordinate
	 * 
	 * @param coord
	 *            Coordinate to get the piece from
	 * @return The Hanto piece at the coordinate
	 */
	HantoPiece getPieceAt(ConcreteHantoCoordinate coord);

	/**
	 * Gets a visual representation of the board
	 * 
	 * @return The string representing the board
	 */
	String getPrintableBoard();
}
