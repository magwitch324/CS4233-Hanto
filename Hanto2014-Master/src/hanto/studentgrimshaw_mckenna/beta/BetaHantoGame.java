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

import hanto.common.HantoException;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.HantoPolicy;

/**
 * The alpha implementation of HantoGame
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoGame extends AbstractHantoGame {

	

	/**
	 * Default constructor of AlphaHantoGame. Takes in the player color of who
	 * moves first
	 * 
	 * @param movesFirst
	 */
	public BetaHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
	}

	@Override
	protected void movePiece() throws HantoException {
		throw new HantoException("No piece movement is allowed");
	}





}
