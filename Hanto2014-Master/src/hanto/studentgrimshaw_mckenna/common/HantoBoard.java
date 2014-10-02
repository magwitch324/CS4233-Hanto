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
import hanto.studentgrimshaw_mckenna.common.interfaces.PlacementNeighborValidator;

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
	 * Places a piece on the board in the specified location
	 * 
	 * @param player
	 *            The player placing the piece
	 * @param halfTurns
	 *            The number of half turns that have occurred
	 * @param pieceType
	 *            Type of piece
	 * @param to
	 *            Location to place the piece
	 * @throws HantoException
	 */
	void placePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to);

	void checkCanPlacePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate to) throws HantoException;
	/**
	 * Determines whether a specified coordinate on the board is completely
	 * surrounded by pieces
	 * 
	 * @param center
	 *            Coordinate to test for
	 * @return True if the coordinate is surrounded
	 */
	boolean isCoordinateSurrounded(ConcreteHantoCoordinate center);

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

	ConcreteHantoPiece checkMovePiece(HantoPlayerColor color, HantoPieceType pieceType, ConcreteHantoCoordinate from,
			ConcreteHantoCoordinate to) throws HantoException;

	void movePiece(ConcreteHantoPiece piece, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to);
	
	void setPlacementNeighborValidator(PlacementNeighborValidator validator);
}
