/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.tournament.HantoMoveRecord;

/**
 * Interface for GameAis.
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface GameAi extends HantoGame {

	/**
	 * Determines a move to make and makes it
	 * 
	 * @return HantoMoveRecord of the move
	 * @throws HantoException
	 */
	HantoMoveRecord MakeAiMove() throws HantoException;

}
