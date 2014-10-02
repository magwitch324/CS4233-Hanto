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

import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoPolicy;

/**
 * Beta Implementation of HantoPolicy. Starts the Players with one butterfly and
 * 5 sparrows
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoPolicy extends AbstractHantoPolicy {

	public BetaHantoPolicy(HantoGameID id, HantoPlayerColor movesFirst) {
		super(id, movesFirst);
	}

	@Override
	public int getMaxTurns() {
		return 6;
	}

}
