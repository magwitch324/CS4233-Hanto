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

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.abstracts.AbstractHantoPolicy;

/**
 * Gamma implementation of hantoPolicy containing the rules for delta game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class GammaHantoPolicy extends AbstractHantoPolicy {

	/**
	 * Default constructor for GammaHantoPolicy
	 * 
	 * @param id
	 *            Version of the game
	 * @param movesFirst
	 *            Color of the player to move first
	 */
	public GammaHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		super(id, movesFirst);
	}

	@Override
	public int getMaxTurns() {
		return 20;
	}

}
