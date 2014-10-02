/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentgrimshaw_mckenna.common.validators;

import hanto.studentgrimshaw_mckenna.common.ConcreteHantoPiece;
import hanto.studentgrimshaw_mckenna.common.interfaces.PlacementNeighborValidator;

/**
 * Placement validator for placing next to allied pieces only
 * 
 * @author Twgrimshaw
 * @author Remckenna
 *
 */
public class AllyNeighborPlacementValidator implements PlacementNeighborValidator {

	@Override
	public boolean isValidNeighborPiece(ConcreteHantoPiece piece, ConcreteHantoPiece neighbor) {
		return neighbor.getColor().equals(piece.getColor());
	}

}
