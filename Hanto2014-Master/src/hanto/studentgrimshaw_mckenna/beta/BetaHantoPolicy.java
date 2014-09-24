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

import java.util.HashMap;
import java.util.Map;

/**
 * Beta Implementation of HantoPolicy. Starts the Players with one butterfly and
 * 5 sparrows
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoPolicy implements HantoPolicy {

	private HantoPlayerColor movesFirst;
	private HantoPlayerColor movesSecond;

	public BetaHantoPolicy(HantoPlayerColor movesFirst) {
		this.movesFirst = movesFirst;
		movesSecond = movesFirst == HantoPlayerColor.BLUE ? HantoPlayerColor.RED : HantoPlayerColor.BLUE;
	}

	@Override
	public Map<HantoPieceType, Integer> getStartingHand() {

		Map<HantoPieceType, Integer> hand = new HashMap<HantoPieceType, Integer>();

		hand.put(HantoPieceType.BUTTERFLY, 1);
		hand.put(HantoPieceType.SPARROW, 5);

		return hand;
	}
	
	@Override
	public HantoPlayer constructPlayer1() {
		HantoPlayer player1 = new BetaHantoPlayer(movesFirst);
		player1.setStartingHand(getStartingHand());
		return player1;
	}

	@Override
	public HantoPlayer constructPlayer2() {
		HantoPlayer player2 = new BetaHantoPlayer(movesSecond);
		player2.setStartingHand(getStartingHand());
		return player2;
	}

	@Override
	public int getMaxTurns() {
		return 6;
	}
	
	

}
