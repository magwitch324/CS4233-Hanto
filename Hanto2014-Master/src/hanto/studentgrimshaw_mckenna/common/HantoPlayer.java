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

import java.util.Map;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * Holds information relating the the player's hand and the pieces in it
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface HantoPlayer {

	/**
	 * Determines whether or not the player can place the specified piece
	 * 
	 * @param halfTurns
	 *            Half turns that have taken place so far
	 * @param type
	 *            Type of the piece looking to be placed
	 * @return True if the player can place the piece
	 */
	boolean canPlacePiece(int turnNumber, HantoPieceType type);

	/**
	 * Gets the color associated with the player
	 * 
	 * @return The player's color
	 */
	HantoPlayerColor getColor();

	/**
	 * Decreases the piece count in the players hand for the specified piece
	 * type
	 * 
	 * @param pieceType
	 *            The type of piece for which to decrement the count
	 */
	void decrementPieceCount(HantoPieceType pieceType);

	void setStartingHand(Map<HantoPieceType, Integer> startingHand);

	boolean canMovePiece();
}
