/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common;

import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;

import java.util.Map;

/**
 * Stores any rules specific to the version of hanto game
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface HantoPolicy {

	
	HantoPlayer constructPlayer1();
	HantoPlayer constructPlayer2();
	
	HantoGameID getId();
	
	int getMaxTurns();
	
}
