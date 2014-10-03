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

import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoGame;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoBoard;
import hanto.studentgrimshaw_mckenna.common.interfaces.HantoPolicy;

/**
 * Delta implementation of hanto game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class DeltaHantoGame extends AbstractHantoGame {

	/**
	 * Default constructor for DeltaHantoGame
	 * 
	 * @param policy
	 *            Rules for the game
	 * @param board
	 *            Board on which the game is played
	 */
	public DeltaHantoGame(HantoPolicy policy, HantoBoard board) {
		super(policy, board);
	}

}
