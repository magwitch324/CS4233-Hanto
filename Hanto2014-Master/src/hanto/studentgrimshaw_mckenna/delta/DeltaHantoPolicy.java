/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.delta;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoPolicy;

/**
 * Delta implementation of hantoPolicy containing the rules for delta game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class DeltaHantoPolicy extends AbstractHantoPolicy {

	/**
	 * Default constructor for DeltaHantoPolicy
	 * 
	 * @param id
	 *            Version of the game
	 * @param movesFirst
	 *            Color of the player to move first
	 */
	public DeltaHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		super(id, movesFirst);
	}

	@Override
	protected Map<HantoPieceType, Integer> getStartingHand() {

		Map<HantoPieceType, Integer> hand = new HashMap<HantoPieceType, Integer>();

		hand.put(HantoPieceType.BUTTERFLY, 1);
		hand.put(HantoPieceType.SPARROW, 4);
		hand.put(HantoPieceType.CRAB, 4);

		return hand;
	}

}
