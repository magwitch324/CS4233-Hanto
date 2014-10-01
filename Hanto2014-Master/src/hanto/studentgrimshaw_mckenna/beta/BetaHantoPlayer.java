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

import hanto.common.HantoPlayerColor;
import hanto.studentgrimshaw_mckenna.common.AbstractHantoPlayer;

/**
 * Beta Implementation
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class BetaHantoPlayer extends AbstractHantoPlayer {
	/**
	 * The default constructor for BetaHantoPlayer
	 * 
	 * @param policy
	 *            The game policy that gives the player their starting hand
	 * @param color
	 *            The color of the player
	 */
	public BetaHantoPlayer(HantoPlayerColor color) {
		super(color);
	}
	
	@Override
	public boolean canMovePiece() {
		return false;
	}

}
