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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPlayer;

import java.util.Map;

/**
 * Concrete implementation of HantoPlayer
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class ConcreteHantoPlayer implements HantoPlayer {

	private HantoPlayerColor color;
	private Map<HantoPieceType, Integer> hand;

	/**
	 * The default constructor for ConcreteHantoPlayer
	 * 
	 * @param color
	 *            The color of the player
	 */
	public ConcreteHantoPlayer(HantoPlayerColor color) {
		this.color = color;
	}

	@Override
	public void setStartingHand(Map<HantoPieceType, Integer> startingHand) {
		hand = startingHand;
	}

	@Override
	public boolean canPlacePiece(int turnNumber, HantoPieceType type) {
		boolean canPlace = false;
		// if the piece type is valid
		if (hand.containsKey(type)) {
			// if its the fourth turn and the butterfly hasn't been placed yet
			if (turnNumber > 3 && hand.get(HantoPieceType.BUTTERFLY) != 0) {
				// Make sure the piece being placed is a butterfly
				canPlace = (type == HantoPieceType.BUTTERFLY);
			} else {
				canPlace = hand.get(type) > 0;
			}
		}
		return canPlace;
	}

	@Override
	public void decrementPieceCount(HantoPieceType pieceType) {
		int remainingPieces = hand.get(pieceType);
		remainingPieces--;
		hand.put(pieceType, remainingPieces);
	}

	@Override
	public boolean canMovePieces() {
		return hand.get(HantoPieceType.BUTTERFLY) == 0;
	}

	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

}
