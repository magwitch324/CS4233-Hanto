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

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;

/**
 * Interface for placement validators
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public interface PlacementNeighborValidator {

	/**
	 * Determines if the neighboring piece is valid or not
	 * 
	 * @param piece
	 *            Current piece
	 * @param neighbor
	 *            Neighboring piece
	 * @return True if the neighbor is valid
	 */
	boolean isValidNeighborPiece(ConcreteHantoPiece piece, ConcreteHantoPiece neighbor);

}
