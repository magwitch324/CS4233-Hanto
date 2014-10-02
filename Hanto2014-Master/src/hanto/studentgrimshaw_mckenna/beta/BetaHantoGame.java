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
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.ConcreteHantoCoordinate;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

/**
 * The alpha implementation of HantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoGame extends AbstractHantoGame {

	/**
	 * Default constructor of BetaHantoGame. Takes in the policy and the board
	 * 
	 * @param policy
	 *            Policy that sets the rules for the game
	 * @param board
	 *            Board on which the game is played
	 */
	public BetaHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
	}

	@Override
	protected void movePiece(HantoPieceType pieceType, ConcreteHantoCoordinate from, ConcreteHantoCoordinate to)
			throws HantoException {
		throw new HantoException("No piece movement is allowed");
	}

	@Override
	protected MoveResult checkResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo) {
		return null;
	}

}
