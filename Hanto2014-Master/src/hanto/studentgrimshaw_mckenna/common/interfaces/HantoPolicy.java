/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.interfaces;

import hanto.common.HantoGameID;

/**
 * Interface for storing any rules specific to the version of hanto game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface HantoPolicy {

	/**
	 * Constructs player 1
	 * 
	 * @return Player1
	 */
	HantoPlayer constructPlayer1();

	/**
	 * Constructs player 2
	 * 
	 * @return Player2
	 */
	HantoPlayer constructPlayer2();

	/**
	 * Gets the game version
	 * 
	 * @return Game Version
	 */
	HantoGameID getId();

	/**
	 * Gets the maximum number of turns allowed
	 * 
	 * @return maxTurns
	 */
	int getMaxTurns();

}
