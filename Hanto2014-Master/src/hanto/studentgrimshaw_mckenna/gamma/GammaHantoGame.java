/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.common.MoveResult;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

/**
 * Gamma implementation of hanto game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class GammaHantoGame extends AbstractHantoGame {

	/**
	 * Default constructor for GammaHantoGame
	 * 
	 * @param policy
	 *            Rules for the game
	 * @param board
	 *            Board on which the game is played
	 */
	public GammaHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected MoveResult checkResigning(HantoPieceType pieceType, HantoCoordinate hFrom, HantoCoordinate hTo) {
		return null;
	}
}
