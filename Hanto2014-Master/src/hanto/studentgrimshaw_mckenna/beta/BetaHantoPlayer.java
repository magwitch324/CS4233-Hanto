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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.HantoPlayer;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;

import java.util.Map;

/**
 * Beta Implementation
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoPlayer implements HantoPlayer {
	private HantoPlayerColor color;
	private Map<HantoPieceType, Integer> hand;

	/**
	 * The default constructor for BetaHantoPlayer
	 * 
	 * @param policy
	 *            The game policy that gives the player their starting hand
	 * @param color
	 *            The color of the player
	 */
	BetaHantoPlayer(HantoPolicy policy, HantoPlayerColor color) {
		hand = policy.getStartingHand();
		this.color = color;
	}

	@Override
	public boolean canPlacePiece(double halfTurns, HantoPieceType type) {
		boolean canPlace = false;
		// if the piece type is valid
		if (hand.containsKey(type)) {
			// if its the fourth turn and the butterfly hasnt been placed yet
			if (halfTurns / 2 > 3 && hand.get(HantoPieceType.BUTTERFLY) != 0) {
				// Make sure the piece being placed is a butterfly
				canPlace = (type == HantoPieceType.BUTTERFLY);
			} else {
				canPlace = hand.get(type) > 0;
			}
		}
		return canPlace;
	}

	@Override
	public HantoPlayerColor getColor() {
		return color;
	}

	@Override
	public void decrementPieceCount(HantoPieceType pieceType) {
		int remainingPieces = hand.get(pieceType);
		remainingPieces--;
		hand.put(pieceType, remainingPieces);
	}
}